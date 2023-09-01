package io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata;

import com.asyncapi.v2.binding.operation.sqs.SQSOperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.AbstractOperationBindingProcessor;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.SqsAsyncOperationBinding;
import org.springframework.stereotype.Component;

@Component
public class SqsOperationBindingProcessor extends AbstractOperationBindingProcessor<SqsAsyncOperationBinding> {

    @Override
    protected ProcessedOperationBinding mapToOperationBinding(SqsAsyncOperationBinding bindingAnnotation) {
        return new ProcessedOperationBinding(bindingAnnotation.type(), new SQSOperationBinding());
    }
}
