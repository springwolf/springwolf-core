package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedMessageBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class AsyncAnnotationScannerUtil {
    public static AsyncHeaders getAsyncHeaders(AsyncOperation op, StringValueResolver resolver) {
        if (op.headers().values().length == 0) {
            return AsyncHeaders.NOT_DOCUMENTED;
        }

        AsyncHeaders asyncHeaders = new AsyncHeaders(op.headers().schemaName());
        Arrays.stream(op.headers().values())
                .collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((headerName, headers) -> {
                    List<String> values = getHeaderValues(headers, resolver);
                    String exampleValue = values.stream().findFirst().orElse(null);
                    asyncHeaders.addHeader(
                            AsyncHeaderSchema
                                    .headerBuilder()
                                    .headerName(resolver.resolveStringValue(headerName))
                                    .description(getDescription(headers, resolver))
                                    .enumValue(values)
                                    .example(exampleValue)
                                    .build()
                    );
                });

        return asyncHeaders;
    }

    private static List<String> getHeaderValues(List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value
                .stream()
                .map(AsyncOperation.Headers.Header::value)
                .map(resolver::resolveStringValue)
                .sorted()
                .collect(Collectors.toList());
    }

    private static String getDescription(List<AsyncOperation.Headers.Header> value, StringValueResolver resolver) {
        return value
                .stream()
                .map(AsyncOperation.Headers.Header::description)
                .map(resolver::resolveStringValue)
                .filter(StringUtils::isNotBlank)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public static Map<String, OperationBinding> processOperationBindingFromAnnotation(Method method, List<OperationBindingProcessor> operationBindingProcessors) {
        return operationBindingProcessors.stream()
                .map(operationBindingProcessor -> operationBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(ProcessedOperationBinding::getType, ProcessedOperationBinding::getBinding));
    }

    public static Map<String, MessageBinding> processMessageBindingFromAnnotation(Method method, List<MessageBindingProcessor> messageBindingProcessors) {
        return messageBindingProcessors.stream()
                .map(messageBindingProcessor -> messageBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(ProcessedMessageBinding::getType, ProcessedMessageBinding::getBinding));
    }
}
