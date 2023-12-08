// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.MessageBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.bindings.OperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.AnnotationUtil;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.AbstractOperationDataScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.PayloadClassExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.OperationData;
import io.github.stavshamir.springwolf.asyncapi.types.ProducerData;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.Message;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class AsyncPublisherAnnotationScanner extends AbstractOperationDataScanner
        implements EmbeddedValueResolverAware {
    private StringValueResolver resolver;
    private final ClassScanner classScanner;
    private final SchemasService schemasService;
    private final AsyncApiDocketService asyncApiDocketService;

    private final PayloadClassExtractor payloadClassExtractor;

    private final List<OperationBindingProcessor> operationBindingProcessors;
    private final List<MessageBindingProcessor> messageBindingProcessors;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected SchemasService getSchemaService() {
        return this.schemasService;
    }

    @Override
    protected AsyncApiDocketService getAsyncApiDocketService() {
        return asyncApiDocketService;
    }

    @Override
    protected List<OperationData> getOperationData() {
        return classScanner.scan().stream()
                .flatMap(this::getAnnotatedMethods)
                .flatMap(this::toOperationData)
                .toList();
    }

    private Stream<Method> getAnnotatedMethods(Class<?> type) {
        Class<AsyncPublisher> annotationClass = AsyncPublisher.class;
        log.debug("Scanning class \"{}\" for @\"{}\" annotated methods", type.getName(), annotationClass.getName());

        return Arrays.stream(type.getDeclaredMethods())
                .filter(method -> !method.isBridge())
                .filter(method -> AnnotationUtil.findAnnotation(annotationClass, method) != null);
    }

    private Stream<OperationData> toOperationData(Method method) {
        log.debug("Mapping method \"{}\" to channels", method.getName());

        Map<String, OperationBinding> operationBindings =
                AsyncAnnotationScannerUtil.processOperationBindingFromAnnotation(method, operationBindingProcessors);
        Map<String, MessageBinding> messageBindings =
                AsyncAnnotationScannerUtil.processMessageBindingFromAnnotation(method, messageBindingProcessors);
        Message message = AsyncAnnotationScannerUtil.processMessageFromAnnotation(method);

        Set<AsyncPublisher> annotations = AnnotationUtil.findAnnotations(AsyncPublisher.class, method);
        return annotations.stream()
                .map(annotation -> toConsumerData(method, operationBindings, messageBindings, message, annotation));
    }

    private ProducerData toConsumerData(
            Method method,
            Map<String, OperationBinding> operationBindings,
            Map<String, MessageBinding> messageBindings,
            Message message,
            AsyncPublisher annotation) {
        AsyncOperation op = annotation.operation();
        Class<?> payloadType =
                op.payloadType() != Object.class ? op.payloadType() : payloadClassExtractor.extractFrom(method);
        return ProducerData.builder()
                .channelName(resolver.resolveStringValue(op.channelName()))
                .description(resolver.resolveStringValue(op.description()))
                .servers(AsyncAnnotationScannerUtil.getServers(op, resolver))
                .headers(AsyncAnnotationScannerUtil.getAsyncHeaders(op, resolver))
                .payloadType(payloadType)
                .operationBinding(operationBindings)
                .messageBinding(messageBindings)
                .message(message)
                .build();
    }

    @Override
    protected OperationData.OperationType getOperationType() {
        return OperationData.OperationType.SUBSCRIBE;
    }
}
