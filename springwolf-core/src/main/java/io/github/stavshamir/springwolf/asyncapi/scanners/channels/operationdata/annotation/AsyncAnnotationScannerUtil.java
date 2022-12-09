package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation;

import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.ProcessedOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaderSchema;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import org.springframework.util.StringUtils;

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

        AsyncHeaders headers = new AsyncHeaders(op.headers().schemaName());
        Arrays.stream(op.headers().values()).collect(groupingBy(AsyncOperation.Headers.Header::name))
                .forEach((key, value) -> {
                    List<String> descriptions = value.stream().map(AsyncOperation.Headers.Header::description).filter(it -> !"".equals(it)).sorted().collect(Collectors.toList());
                    List<String> values = value.stream().map(AsyncOperation.Headers.Header::value).sorted().collect(Collectors.toList());
                    headers.addHeader(
                            AsyncHeaderSchema
                                    .headerBuilder()
                                    .headerName(key)
                                    .description(descriptions.stream().findFirst().orElse(null))
                                    .enumValue(values)
                                    .example(values.stream().findFirst().orElse(null))
                                    .build()
                    );
                });

        return headers;
    }

    public static String nullIfEmpty(String stringValue) {
        return StringUtils.isEmpty(stringValue) ? null : stringValue;
    }

    public static Map<String, OperationBinding> processBindingFromAnnotation(Method method, List<OperationBindingProcessor> operationBindingProcessors) {
        return operationBindingProcessors.stream()
                .map(operationBindingProcessor -> operationBindingProcessor.process(method))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(ProcessedOperationBinding::getType, ProcessedOperationBinding::getBinding));
    }
}
