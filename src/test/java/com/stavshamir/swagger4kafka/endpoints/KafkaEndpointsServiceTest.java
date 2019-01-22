package com.stavshamir.swagger4kafka.endpoints;

import com.google.common.collect.Sets;
import com.stavshamir.swagger4kafka.configuration.Docket;
import com.stavshamir.swagger4kafka.endpoints.consumers.KafkaConsumerClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KafkaEndpointsServiceTest {

    @Mock
    private KafkaListenersScanner kafkaListenersScanner;

    private final static String TOPIC = "test-topic";

    @Test
    public void getEndpoints_docketIsSet() {
        // Given docket is set and basePackage is set
        MockitoAnnotations.initMocks(this);
        Docket docket = Docket.builder()
                .basePackage("com.stavshamir.swagger4kafka.endpoints.consumers")
                .build();
        KafkaEndpointsService endpointsService = new KafkaEndpointsService(docket, kafkaListenersScanner);

        // Given a A class annotated with @Component and contains a method annotated with @KafkaListener
        KafkaEndpoint endpoint = KafkaEndpoint.builder()
                .topic(TOPIC)
                .build();
        when(kafkaListenersScanner.getKafkaEndpointsFromClass(KafkaConsumerClass.class))
                .thenReturn(Sets.newHashSet(endpoint));

        // When getEndpoints is called
        Set<KafkaEndpoint> endpoints = endpointsService.getEndpoints();

        // Then the returned set contains an endpoint
        assertThat(endpoints)
                .containsExactly(endpoint);
    }

    @Test
    public void getEndpoints_basePackageIsNotSet() {
        // Given docket is set but basePackage is not
        MockitoAnnotations.initMocks(this);
        Docket docket = Docket.builder().build();
        KafkaEndpointsService endpointsService = new KafkaEndpointsService(docket, kafkaListenersScanner);

        // When KafkaEndpointsService's constructor is called
        // Then an exception is raised
        assertThatThrownBy(endpointsService::getEndpoints)
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Base package not provided - please provide a Docket bean with basePackage defined");
    }

}