package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.AbstractOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.ConsumerData;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(value = ChannelPriority.ASYNC_ANNOTATION)
public class AsyncListenerAnnotationScanner extends AbstractOperationDataScanner implements EmbeddedValueResolverAware {
    private StringValueResolver resolver;
    private final ComponentClassScanner componentClassScanner;
    private final SchemasService schemasService;

    private final List<OperationBindingProcessor> operationBindingProcessors;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected SchemasService getSchemaService() {
        return this.schemasService;
    }

    @Override
    protected List<OperationData> getOperationData() {
        return componentClassScanner.scan().stream()
                .map(this::getAnnotatedMethods)
                .flatMap(Collection::stream)
                .map(this::mapMethodToOperationData)
                .collect(Collectors.toList());
    }

    private Set<Method> getAnnotatedMethods(Class<?> type) {
        Class<AsyncListener> annotationClass = AsyncListener.class;
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(toSet());
    }

    private OperationData mapMethodToOperationData(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Map<String, OperationBinding> operationBindings = AsyncAnnotationScannerUtil.processBindingFromAnnotation(method, operationBindingProcessors);

        Class<AsyncListener> annotationClass = AsyncListener.class;
        AsyncListener annotation = Optional.of(method.getAnnotation(annotationClass))
                .orElseThrow(() -> new IllegalArgumentException("Method must be annotated with " + annotationClass.getName()));

        AsyncOperation op = annotation.operation();
        Class<?> payloadType = op.payloadType() != Object.class ? op.payloadType() :
                SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
        return ConsumerData.builder()
                .channelName(resolver.resolveStringValue(op.channelName()))
                .description(resolver.resolveStringValue(op.description()))
                .headers(AsyncAnnotationScannerUtil.getAsyncHeaders(op))
                .payloadType(payloadType)
                .operationBinding(operationBindings)
                .build();
    }

    @Override
    protected OperationData.OperationType getOperationType() {
        return OperationData.OperationType.PUBLISH;
    }
}
