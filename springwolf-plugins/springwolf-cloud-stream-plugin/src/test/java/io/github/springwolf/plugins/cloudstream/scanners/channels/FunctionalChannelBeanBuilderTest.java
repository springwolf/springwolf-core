// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.scanners.channels;

import io.github.springwolf.core.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.apache.kafka.streams.kstream.KStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.springwolf.plugins.cloudstream.scanners.channels.FunctionalChannelBeanData.BeanType.CONSUMER;
import static io.github.springwolf.plugins.cloudstream.scanners.channels.FunctionalChannelBeanData.BeanType.SUPPLIER;

class FunctionalChannelBeanBuilderTest {
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder =
            new FunctionalChannelBeanBuilder(new PayloadClassExtractor(properties));

    @Nested
    class NoBean {
        @Test
        void testNotAFunctionalChannelBean() throws NoSuchMethodException {
            Method method = getMethod(this.getClass(), "notAFunctionalChannelBean");

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data).isEmpty();
        }

        @Bean
        private String notAFunctionalChannelBean() {
            return "foo";
        }
    }

    @Nested
    class consumerBean {
        @Test
        void testConsumerBean() throws NoSuchMethodException {
            Method method = getMethod(this.getClass(), "consumerBean");

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data)
                    .containsExactly(
                            new FunctionalChannelBeanData("consumerBean", String.class, CONSUMER, "consumerBean-in-0"));
        }

        @Bean
        private Consumer<String> consumerBean() {
            return System.out::println;
        }
    }

    @Nested
    class SupplierBean {
        @Test
        void testSupplierBean() throws NoSuchMethodException {
            Method method = getMethod(this.getClass(), "supplierBean");

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data)
                    .containsExactly(new FunctionalChannelBeanData(
                            "supplierBean", String.class, SUPPLIER, "supplierBean-out-0"));
        }

        @Bean
        private Supplier<String> supplierBean() {
            return () -> "foo";
        }
    }

    @Nested
    class FunctionBean {
        @Test
        void testFunctionBean() throws NoSuchMethodException {
            Method method = getMethod(this.getClass(), "functionBean");

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data)
                    .containsExactlyInAnyOrder(
                            new FunctionalChannelBeanData("functionBean", String.class, CONSUMER, "functionBean-in-0"),
                            new FunctionalChannelBeanData(
                                    "functionBean", Integer.class, SUPPLIER, "functionBean-out-0"));
        }

        @Bean
        private Function<String, Integer> functionBean() {
            return s -> 1;
        }
    }

    @Nested
    class ConsumerBeanWithGenericPayload {

        @Test
        void testConsumerBeanWithGenericPayload() throws NoSuchMethodException {
            String methodName = "consumerBeanWithGenericPayload";
            Method method = getMethod(this.getClass(), methodName);

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data)
                    .containsExactly(
                            new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
        }

        @Bean
        private Consumer<Message<String>> consumerBeanWithGenericPayload() {
            return System.out::println;
        }
    }

    @Nested
    class KStreamBean {

        @Test
        void testKafkaStreamsConsumerBean() throws NoSuchMethodException {
            String methodName = "kafkaStreamsConsumerBean";
            Method method = getMethod(this.getClass(), methodName);

            Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.fromMethodBean(method);

            Assertions.assertThat(data)
                    .containsExactly(
                            new FunctionalChannelBeanData(methodName, String.class, CONSUMER, methodName + "-in-0"));
        }

        @Bean
        private Consumer<KStream<Void, String>> kafkaStreamsConsumerBean() {
            return System.out::println;
        }
    }

    private static Method getMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(methodName);
    }
}
