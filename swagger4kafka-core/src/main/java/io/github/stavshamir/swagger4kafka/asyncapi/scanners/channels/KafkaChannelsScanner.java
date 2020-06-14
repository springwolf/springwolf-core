package io.github.stavshamir.swagger4kafka.asyncapi.scanners.channels;

import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.Channel;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.Operation;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.swagger4kafka.asyncapi.types.channel.operation.message.PayloadReference;
import io.github.stavshamir.swagger4kafka.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.*;

@Slf4j
@Service(ScannerBeanNames.KAFKA_SCANNER)
@RequiredArgsConstructor
public class KafkaChannelsScanner implements ChannelsScanner, EmbeddedValueResolverAware {

    private final SchemasService schemasService;
    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public Map<String, Channel> scanForChannels(Set<Class<?>> classes) {
        return classes.stream()
                .map(this::getMethodsAnnotatedWithKafkaListener).flatMap(Collection::stream)
                .map(this::mapMethodToChannels)
                .map(Map::entrySet).flatMap(Collection::stream)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Set<Method> getMethodsAnnotatedWithKafkaListener(Class<?> type) {
        log.debug("Scanning class \"{}\" for @KafkaListener annotated methods", type.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(KafkaListener.class))
                .collect(toSet());
    }

    private Map<String, Channel> mapMethodToChannels(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        KafkaListener kafkaListenerAnnotation = Optional.of(method.getAnnotation(KafkaListener.class))
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with @KafkaListener"));

        return getTopics(kafkaListenerAnnotation).stream()
                .collect(toMap(topic -> topic, $ -> mapMethodToChannel(method)));
    }

    private List<String> getTopics(KafkaListener kafkaListenerAnnotation) {
        List<String> resolvedTopics = Arrays.stream(kafkaListenerAnnotation.topics())
                .map(resolver::resolveStringValue)
                .collect(toList());

        log.debug("Found topics: {}", String.join(", ", resolvedTopics));
        return resolvedTopics;
    }

    private Channel mapMethodToChannel(Method method) {
        Class<?> payloadType = getPayloadType(method);
        String modelName = schemasService.register(payloadType);

        Message message = Message.builder()
                .name(payloadType.getName())
                .title(modelName)
                .payload(PayloadReference.fromModelName(modelName))
                .build();

        Operation operation = Operation.builder()
                .message(message)
                .build();

        return Channel.builder()
                .subscribe(operation)
                .build();
    }

    private Class<?> getPayloadType(Method method) {
        log.debug("Finding payload type for {}", method.getName());

        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Only single parameter KafkaListener methods are supported");
        }

        return parameterTypes[0];
    }

}
