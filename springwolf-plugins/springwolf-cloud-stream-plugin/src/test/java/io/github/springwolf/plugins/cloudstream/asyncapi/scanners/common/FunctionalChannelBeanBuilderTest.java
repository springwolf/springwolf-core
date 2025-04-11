// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common;

import io.github.springwolf.core.asyncapi.scanners.common.payload.internal.TypeExtractor;
import io.github.springwolf.core.configuration.properties.SpringwolfConfigProperties;
import org.apache.kafka.streams.kstream.KStream;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData.BeanType.CONSUMER;
import static io.github.springwolf.plugins.cloudstream.asyncapi.scanners.common.FunctionalChannelBeanData.BeanType.SUPPLIER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class FunctionalChannelBeanBuilderTest {
    private final SpringwolfConfigProperties properties = new SpringwolfConfigProperties();
    private final FunctionalChannelBeanBuilder functionalChannelBeanBuilder =
            new FunctionalChannelBeanBuilder(new TypeExtractor(properties));

    @Nested
    class FromMethod {

        @Nested
        class NotAFunctionalBean {
            @Test
            void testNotAFunctionalChannelBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "notAFunctionalChannelBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data).isEmpty();
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

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "consumerBean", method, String.class, CONSUMER, "consumerBean-in-0"));
            }

            @Bean
            private Consumer<String> consumerBean() {
                return System.out::println;
            }
        }

        @Nested
        class BiConsumerBean {
            @Test
            void testBiConsumerBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biConsumerBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "biConsumerBean", method, String.class, CONSUMER, "biConsumerBean-in-0"));
            }

            @Bean
            private BiConsumer<String, Map<String, Object>> biConsumerBean() {
                return (input, headers) -> System.out.println(input);
            }
        }

        @Nested
        class SupplierBean {
            @Test
            void testSupplierBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "supplierBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
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

                assertThat(data)
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
        class BiFunctionBean {
            @Test
            void testBiFunctionBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biFunctionBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactlyInAnyOrder(
                                new FunctionalChannelBeanData(
                                        "biFunctionBean", method, String.class, CONSUMER, "biFunctionBean-in-0"),
                                new FunctionalChannelBeanData(
                                        "biFunctionBean", method, Integer.class, SUPPLIER, "biFunctionBean-out-0"));
            }

            @Bean
            private BiFunction<String, Map<String, Object>, Integer> biFunctionBean() {
                return (s, headers) -> 1;
            }
        }

        @Nested
        class ConsumerBeanWithGenericPayload {

            @Test
            void testConsumerBeanWithGenericPayload() throws NoSuchMethodException {
                String methodName = "consumerBeanWithGenericPayload";
                Method method = getMethod(this.getClass(), methodName);

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
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

                assertThat(data)
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

                assertThat(data).isEmpty();
            }

            private static class TestClass {}
        }

        @Nested
        class ConsumerClass {
            @Test
            void testConsumerClass() {
                Class<?> testClass = getClassObject(this.getClass(), "TestClass");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(testClass);

                assertThat(data)
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

                assertThat(data)
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

                assertThat(data)
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

                assertThat(data)
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

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "TestClass", testClass, String.class, CONSUMER, "testClass-in-0"));
            }

            static class TestClass implements Consumer<KStream<Void, String>> {
                @Override
                public void accept(KStream<Void, String> voidStringKStream) {}
            }
        }

        @Nested
        class InheritanceTypes {

            @Test
            void testBiConsumerChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biConsumerChildBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "biConsumerChildBean", method, String.class, CONSUMER, "biConsumerChildBean-in-0"));
            }

            @Test
            void testBiConsumerRawChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biConsumerRawChildBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data).isEmpty();
            }

            @Test
            void testSupplierRawChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "supplierRawChild");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data).isEmpty();
            }

            @Test
            void testFunctionRawChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "functionRawChild");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data).isEmpty();
            }

            @Test
            void testBiFunctionRawChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biFunctionRawChild");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data).isEmpty();
            }

            @Test
            void testBiConsumerChildChildBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "biConsumerChildChildBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "biConsumerChildChildBean",
                                method,
                                String.class,
                                CONSUMER,
                                "biConsumerChildChildBean-in-0"));
            }

            @Test
            void testConsumerChildClassBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "consumerChildClassBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "consumerChildClassBean",
                                method,
                                String.class,
                                CONSUMER,
                                "consumerChildClassBean-in-0"));
            }

            @Test
            void testConsumerChildChildClassBean() throws NoSuchMethodException {
                Method method = getMethod(this.getClass(), "consumerChildChildClassBean");

                Set<FunctionalChannelBeanData> data = functionalChannelBeanBuilder.build(method);

                assertThat(data)
                        .containsExactly(new FunctionalChannelBeanData(
                                "consumerChildChildClassBean",
                                method,
                                String.class,
                                CONSUMER,
                                "consumerChildChildClassBean-in-0"));
            }

            interface BiConsumerChild extends BiConsumer<String, String> {}

            interface BiConsumerRawChild extends BiConsumer {}

            interface SupplierRawChild extends Supplier {}

            interface FunctionRawChild extends Function {}

            interface BiFunctionRawChild extends BiFunction {}

            interface BiConsumerChildChild extends BiConsumerChild {}

            static class ConsumerChildClass implements Consumer<String> {
                @Override
                public void accept(final String s) {}
            }

            static class ConsumerChildChildClass extends ConsumerChildClass {}

            @Bean
            private BiConsumerChild biConsumerChildBean() {
                return (input, headers) -> System.out.println(input);
            }

            @Bean
            private BiConsumerRawChild biConsumerRawChildBean() {
                return (input, headers) -> System.out.println(input);
            }

            @Bean
            private SupplierRawChild supplierRawChild() {
                return () -> null;
            }

            @Bean
            private FunctionRawChild functionRawChild() {
                return (input) -> null;
            }

            @Bean
            private BiFunctionRawChild biFunctionRawChild() {
                return (input, headers) -> null;
            }

            @Bean
            private BiConsumerChildChild biConsumerChildChildBean() {
                return (input, headers) -> System.out.println(input);
            }

            @Bean
            private ConsumerChildClass consumerChildClassBean() {
                return new ConsumerChildClass();
            }

            @Bean
            private ConsumerChildChildClass consumerChildChildClassBean() {
                return new ConsumerChildChildClass();
            }
        }
    }

    @Nested
    class ErrorCases {

        @Test
        void elementsOtherThanMethodOrClassThrowException() {
            // given
            AnnotatedType annotatedType = mock(AnnotatedType.class);

            // when
            assertThatThrownBy(() -> {
                        functionalChannelBeanBuilder.build(annotatedType);
                    })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Must be a Method or Class");
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
