package com.stavshamir.springaroo;

import com.google.common.collect.Sets;
import com.stavshamir.springaroo.kafka.consumers.KafkaConsumerClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KafkaEndpointsServiceTest {

    @Mock
    private KafkaListenersScanner kafkaListenersScanner;

    @Spy
    private Docket docket = Docket.builder().basePackage("com.stavshamir.springaroo.kafka.consumers").build();

    @InjectMocks
    private KafkaEndpointsService endpointsService;

    private final static String TOPIC = "test-topic";

    @Test
    public void getEndpoints() {
        // Given a A class annotated with @Component and contains a method annotated with @KafkaListener
        KafkaEndpoint endpoint = KafkaEndpoint.builder()
                .methodName("listenerMethod")
                .payloadType(String.class)
                .topics(new String[] { TOPIC })
                .build();
        when(kafkaListenersScanner.getKafkaEndpoints(KafkaConsumerClass.class))
                .thenReturn(Sets.newHashSet(endpoint));

        // When getEndpoints is called
        Set<KafkaEndpoint> endpoints = endpointsService.getEndpoints();

        // Then the returned set contains an endpoint
        assertThat(endpoints)
                .containsExactly(endpoint);
    }

}