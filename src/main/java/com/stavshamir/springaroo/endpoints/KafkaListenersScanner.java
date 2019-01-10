package com.stavshamir.springaroo.endpoints;

import com.stavshamir.springaroo.model.Models;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.*;

import static java.util.stream.Collectors.toSet;

@Slf4j
class KafkaListenersScanner implements EmbeddedValueResolverAware {

    private StringValueResolver resolver;
    private final Models models;

    KafkaListenersScanner(Models models) {
        this.models = models;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    Set<KafkaEndpoint> getKafkaEndpointsFromClass(Class<?> type) {
        log.debug("Scanning {}", type.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(KafkaListener.class))
                .map(this::createKafkaEndpoints)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    private Set<KafkaEndpoint> createKafkaEndpoints(Method method) {
        KafkaListener annotation = Optional.of(method.getAnnotation(KafkaListener.class))
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with @KafkaListener"));

        return getTopics(annotation).stream()
                .map(topic -> topicToEndpoint(topic, method))
                .collect(toSet());
    }

    private List<String> getTopics(KafkaListener kafkaListener) {
        String[] topics = kafkaListener.topics();

        if (topics.length == 1) {
            String s = resolver.resolveStringValue(topics[0]);
            return Collections.singletonList(s);
        }

        return Arrays.asList(topics);
    }

    private KafkaEndpoint topicToEndpoint(String topic, Method method) {
        Class<?> payloadType = getPayloadType(method);

        return KafkaEndpoint.builder()
                .methodName(method.getName())
                .topic(topic)
                .payloadType(payloadType)
                .payloadExample(models.getExample(payloadType))
                .build();
    }

    private static Class<?> getPayloadType(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length != 1) {
            throw new IllegalArgumentException("Only single parameter KafkaListener methods are supported");
        }

        return parameterTypes[0];
    }

}
