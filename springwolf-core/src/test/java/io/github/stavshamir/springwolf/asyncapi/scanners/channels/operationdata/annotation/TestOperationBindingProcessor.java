package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Optional;

public class TestOperationBindingProcessor implements OperationBindingProcessor {
    public static final String TYPE = "testType";
    public static final OperationBinding BINDING = new OperationBinding();

    @Override
    public Optional<ProcessedOperationBinding> process(Method method) {
        if (method.isAnnotationPresent(TestOperationBinding.class)) {
            return Optional.of(new ProcessedOperationBinding(TYPE, BINDING));
        }
        return Optional.empty();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(value = {ElementType.METHOD})
    public @interface TestOperationBinding {}
}
