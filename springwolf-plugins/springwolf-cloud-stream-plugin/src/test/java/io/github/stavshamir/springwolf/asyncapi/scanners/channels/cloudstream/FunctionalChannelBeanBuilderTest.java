// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream;

import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import org.apache.kafka.streams.kstream.KStream;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream.FunctionalChannelBeanData.BeanType.CONSUMER;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.cloudstream.FunctionalChannelBeanData.BeanType.SUPPLIER;
import static org.assertj.core.api.Assertions.assertThat;

class FunctionalChannelBeanBuilderTest {
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder =
            new FunctionalChannelBeanBuilder(new PayloadClassExtractor());

    @Test
    void testNotAFunctionalChannelBean() throws NoSuchMethodException {
        Method method = getMethod("notAFunctionalChannelBean");

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data).isEmpty();
    }

    @Bean
    private String notAFunctionalChannelBean() {
        return "foo";
    }

    @Test
    void testConsumerBean() throws NoSuchMethodException {
        Method method = getMethod("consumerBean");

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData("consumerBean", String.class, CONSUMER, "consumerBean-in-0"));
    }

    @Bean
    private Consumer<String> consumerBean() {
        return System.out::println;
    }

    @Test
    void testSupplierBean() throws NoSuchMethodException {
        Method method = getMethod("supplierBean");

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData("supplierBean", String.class, SUPPLIER, "supplierBean-out-0"));
    }

    @Bean
    private Supplier<String> supplierBean() {
        return () -> "foo";
    }

    @Test
    void testFunctionBean() throws NoSuchMethodException {
        Method method = getMethod("functionBean");

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactlyInAnyOrder(
                        new FunctionalChannelBeanData("functionBean", String.class, CONSUMER, "functionBean-in-0"),
                        new FunctionalChannelBeanData("functionBean", Integer.class, SUPPLIER, "functionBean-out-0"));
    }

    @Bean
    private Function<String, Integer> functionBean() {
        return s -> 1;
    }

    @Test
    void testConsumerBeanWithGenericPayload() throws NoSuchMethodException {
        String methodName = "consumerBeanWithGenericPayload";
        Method method = getMethod(methodName);

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
    }

    @Bean
    private Consumer<List<String>> consumerBeanWithGenericPayload() {
        return System.out::println;
    }

    @Test
    void testKafkaStreamsConsumerBean() throws NoSuchMethodException {
        String methodName = "kafkaStreamsConsumerBean";
        Method method = getMethod(methodName);

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
    }

    @Bean
    private Consumer<KStream<Void, String>> kafkaStreamsConsumerBean() {
        return System.out::println;
    }

    private static Method getMethod(String methodName) throws NoSuchMethodException {
        return FunctionalChannelBeanBuilderTest.class.getDeclaredMethod(methodName);
    }

    @Bean
    private Consumer<Message<String>> springMessagingConsumerBean() {
        return System.out::println;
    }

    @Test
    void testSpringMessagingConsumerBean() throws NoSuchMethodException {
        String methodName = "springMessagingConsumerBean";
        Method method = getMethod(methodName);

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
    }

    @Bean
    private Consumer<List<Message<String>>> springMessagingBatchConsumerBean() {
        return System.out::println;
    }

    @Test
    void testSpringMessagingBatchConsumerBean() throws NoSuchMethodException {
        String methodName = "springMessagingBatchConsumerBean";
        Method method = getMethod(methodName);

        Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

        assertThat(data)
                .containsExactly(
                        new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
    }
}
