package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfSqsConfigConstants.SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED;

@Slf4j
@Service
@Order(value = ChannelPriority.AUTO_DISCOVERED)
@ConditionalOnProperty(name = SPRINGWOLF_SCANNER_SQS_LISTENER_ENABLED, matchIfMissing = true)
public class MethodLevelSqsListenerScanner extends AbstractMethodLevelListenerScanner<SqsListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<SqsListener> getListenerAnnotationClass() {
        return SqsListener.class;
    }

    @Override
    protected String getChannelName(SqsListener annotation) {
        return SqsListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(SqsListener annotation) {
        return SqsListenerUtil.buildChannelBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(SqsListener annotation) {
        return SqsListenerUtil.buildOperationBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(SqsListener annotation) {
        return SqsListenerUtil.buildMessageBinding();
    }

    protected Class<?> getPayloadType(Method method) {
        return SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
