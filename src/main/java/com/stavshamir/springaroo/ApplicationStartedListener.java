package com.stavshamir.springaroo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Component
public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    private final ApplicationContext context;
    private final KafkaListenersScanner scanner;

    @Autowired
    public ApplicationStartedListener(ApplicationContext context, KafkaListenersScanner kafkaListenersScanner) {
        this.context = context;
        this.scanner = kafkaListenersScanner;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Set<KafkaEndpoint> endpoints = getKafkaEndpoints();
        endpoints.forEach(System.out::println);
    }

    private Set<KafkaEndpoint> getKafkaEndpoints() {
        String[] serviceBeanNames = context.getBeanNamesForAnnotation(Service.class);

        return Arrays.stream(serviceBeanNames)
                .map(context::getBean)
                .map(bean -> scanner.getKafkaEndpoints(bean.getClass()))
                .flatMap(Collection::stream)
                .collect(toSet());
    }

}
