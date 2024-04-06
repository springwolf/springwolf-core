// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.PayloadClassExtractor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.apache.kafka.streams.kstream.KStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData.BeanType.CONSUMER;
import static io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData.BeanType.SUPPLIER;

class FunctionalChannelBeanBuilderTest {
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder =
            new FunctionalChannelBeanBuilder(new PayloadClassExtractor(properties));

    @Nested
    class FromMethod {

        @Nested
        class NotAFunctionalBean {
            @Test
            void testNotAFunctionalChannelBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "notAFunctionalChannelBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data).isEmpty();
            }

            @Bean
            private String notAFunctionalChannelBean() {
                return "foo";
            }
        }

        @Nested
        class ConsumerBean {
            @Test
            void testConsumerBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "consumerBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "consumerBean", method, String.class, CONSUMER, "consumerBean-in-0"));
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

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "supplierBean", method, String.class, SUPPLIER, "supplierBean-out-0"));
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

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data)
                        .containsExactlyInAnyOrder(
                                new FunctionalChannelBeanData(
                                        "functionBean", method, String.class, CONSUMER, "functionBean-in-0"),
                                new FunctionalChannelBeanData(
                                        "functionBean", method, Integer.class, SUPPLIER, "functionBean-out-0"));
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

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "consumerBeanWithGenericPayload",
                                method,
                                String.class,
                                CONSUMER,
                                methodName + "-in-0"));
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

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "kafkaStreamsConsumerBean", method, String.class, CONSUMER, methodName + "-in-0"));
            }

            @Bean
            private Consumer<KStream<Void, String>> kafkaStreamsConsumerBean() {
                return System.out::println;
            }
        }
    }

    @Nested
    class FromClass {

        @Nested
        class NotAFunctionalClass {
            @Test
            void testNotAFunctionalChannelBean() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data).isEmpty();
            }

            private static class TestClass {}
        }

        @Nested
        class ConsumerClass {
            @Test
            void testConsumerClass() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "TestClass", testClass, String.class, CONSUMER, "testClass-in-0"));
            }

            private static class TestClass implements Consumer<String> {
                @Override
                public void accept(String s) {}
            }
        }

        @Nested
        class SupplierClass {
            @Test
            void testSupplierClass() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "TestClass", testClass, String.class, SUPPLIER, "testClass-out-0"));
            }

            private static class TestClass implements Supplier<String> {

                @Override
                public String get() {
                    return "foo";
                }
            }
        }

        @Nested
        class FunctionClass {
            @Test
            void testFunctionClass() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data)
                        .containsExactlyInAnyOrder(
                                new FunctionalChannelBeanData(
                                        "TestClass", testClass, String.class, CONSUMER, "testClass-in-0"),
                                new FunctionalChannelBeanData(
                                        "TestClass", testClass, Integer.class, SUPPLIER, "testClass-out-0"));
            }

            private static class TestClass implements Function<String, Integer> {
                @Override
                public Integer apply(String s) {
                    return null;
                }
            }
        }

        @Nested
        class ConsumerClassWithGenericPayload {

            @Test
            void testConsumerClassWithGenericPayload() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "TestClass", testClass, String.class, CONSUMER, "testClass-in-0"));
            }

            static class TestClass implements Consumer<Message<String>> {

                @Override
                public void accept(Message<String> stringMessage) {}
            }
        }

        @Nested
        class KStreamClass {

            @Test
            void testKafkaStreamsConsumerClass() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                Assertions.assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "TestClass", testClass, String.class, CONSUMER, "testClass-in-0"));
            }

            static class TestClass implements Consumer<KStream<Void, String>> {
                @Override
                public void accept(KStream<Void, String> voidStringKStream) {}
            }
        }
    }

    private static Method getMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
        return clazz.getDeclaredMethod(methodName);
    }

    private static Class<?> getClassObject(Class<?> clazz, String className) {
        return Arrays.stream(clazz.getDeclaredClasses())
                .filter(c -> c.getSimpleName().equals(className))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find class with name " + className));
    }
}
