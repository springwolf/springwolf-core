package io.github.stavshamir.springwolf.asyncapi.scanners.channels;

import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.FunctionalChannelBeanData.BeanType.CONSUMER;
import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.FunctionalChannelBeanData.BeanType.SUPPLIER;
import static org.assertj.core.api.Assertions.assertThat;


public class FunctionalChannelBeanDataTest {

    @Test
    public void testNotAFunctionalChannelBean() throws NoSuchMethodException {
        Method method = getMethod("notAFunctionalChannelBean");
        Set<FunctionalChannelBeanData> data = FunctionalChannelBeanData.fromMethodBean(method);
        assertThat(data).isEmpty();
    }

    @Bean
    private String notAFunctionalChannelBean() {
        return "foo";
    }

    @Test
    public void testConsumerBean() throws NoSuchMethodException {
        Method method = getMethod("consumerBean");
        Set<FunctionalChannelBeanData> data = FunctionalChannelBeanData.fromMethodBean(method);
        assertThat(data)
                .containsExactly(new FunctionalChannelBeanData("consumerBean", String.class, CONSUMER, "consumerBean-in-0"));
    }

    @Bean
    private Consumer<String> consumerBean() {
        return System.out::println;
    }


    @Test
    public void testSupplierBean() throws NoSuchMethodException {
        Method method = getMethod("supplierBean");
        Set<FunctionalChannelBeanData> data = FunctionalChannelBeanData.fromMethodBean(method);
        assertThat(data)
                .containsExactly(new FunctionalChannelBeanData("supplierBean", String.class, SUPPLIER, "supplierBean-out-0"));
    }

    @Bean
    private Supplier<String> supplierBean() {
        return () -> "foo";
    }

    @Test
    public void testFunctionBean() throws NoSuchMethodException {
        Method method = getMethod("functionBean");
        Set<FunctionalChannelBeanData> data = FunctionalChannelBeanData.fromMethodBean(method);
        assertThat(data).containsExactlyInAnyOrder(
                new FunctionalChannelBeanData("functionBean", String.class, CONSUMER, "functionBean-in-0"),
                new FunctionalChannelBeanData("functionBean", Integer.class, SUPPLIER, "functionBean-out-0")
        );
    }

    @Bean
    private Function<String, Integer> functionBean() {
        return s -> 1;
    }

    private static Method getMethod(String methodName) throws NoSuchMethodException {
        return FunctionalChannelBeanDataTest.class.getDeclaredMethod(methodName);
    }

}