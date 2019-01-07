package com.stavshamir.springaroo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Slf4j
class KafkaListenersScanner implements EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    Set<KafkaEndpoint> getKafkaEndpoints(Class<?> type) {
        log.debug("Scanning {}", type.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(KafkaListener.class))
                .map(this::createKafkaEndpoint)
                .collect(toSet());
    }

    private KafkaEndpoint createKafkaEndpoint(Method method) {
        KafkaListener annotation = Optional.of(method.getAnnotation(KafkaListener.class))
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with @KafkaListener"));

        return KafkaEndpoint.builder()
                .methodName(method.getName())
                .topics(getTopics(annotation))
                .payloadType(getPayloadType(method))
                .build();
    }

    private String[] getTopics(KafkaListener kafkaListener) {
        String[] topics = kafkaListener.topics();

        if (topics.length == 1) {
            String s = resolver.resolveStringValue(topics[0]);
            return new String[]{s};
        }

        return topics;
    }

    private static Class<?> getPayloadType(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Only single parameter KafkaListener methods are supported");
        }

        return parameterTypes[0];
    }

}
