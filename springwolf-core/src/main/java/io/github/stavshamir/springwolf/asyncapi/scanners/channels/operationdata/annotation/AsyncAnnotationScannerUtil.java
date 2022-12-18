package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

class AsyncAnnotationScannerUtil {
    public static AsyncHeaders getAsyncHeaders(AsyncOperation op) {
        if (op.headers().values().length == 0) {
            return AsyncHeaders.NOT_DOCUMENTED;
        }

        AsyncHeaders asyncHeaders = new AsyncHeaders(op.headers().schemaName());
        Arrays.stream(op.headers().values())
                .collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((headerName, headers) -> {
                    List<String> values = getHeaderValues(headers);
                    String exampleValue = values.stream().findFirst().orElse(null);
                    asyncHeaders.addHeader(
                            AsyncHeaderSchema
                                    .headerBuilder()
                                    .headerName(headerName)
                                    .description(getDescription(headers))
                                    .enumValue(values)
                                    .example(exampleValue)
                                    .build()
                    );
                });

        return asyncHeaders;
    }

    private static List<String> getHeaderValues(List<AsyncOperation.Headers.Header> value) {
        return value
                .stream()
                .map(AsyncOperation.Headers.Header::value)
                .sorted()
                .collect(Collectors.toList());
    }

    private static String getDescription(List<AsyncOperation.Headers.Header> value) {
        return value
                .stream()
                .map(AsyncOperation.Headers.Header::description)
                .filter(StringUtils::isNotBlank)
                .sorted()
                .findFirst()
                .orElse(null);
    }

    public static Map<String, OperationBinding> processBindingFromAnnotation(Method method, List<OperationBindingProcessor> operationBindingProcessors) {
        return operationBindingProcessors.stream()
                .map(operationBindingProcessor -> operationBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(ProcessedOperationBinding::getType, ProcessedOperationBinding::getBinding));
    }
}
