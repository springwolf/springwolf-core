// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Slf4j
public class ClassLevelRabbitListenerScanner extends AbstractClassLevelListenerScanner<RabbitListener, RabbitHandler>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private final RabbitListenerUtil.RabbitListenerUtilContext context;
    private StringValueResolver resolver;

    public ClassLevelRabbitListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor,
            List<Queue> queues,
            List<Exchange> exchanges,
            List<Binding> bindings) {
        super(componentClassScanner, schemasService, springPayloadAnnotationTypeExtractor);
        context = RabbitListenerUtil.RabbitListenerUtilContext.create(queues, exchanges, bindings);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<RabbitListener> getListenerAnnotationClass() {
        return RabbitListener.class;
    }

    @Override
    protected Class<RabbitHandler> getHandlerAnnotationClass() {
        return RabbitHandler.class;
    }

    @Override
    protected String getChannelName(RabbitListener annotation) {
        return RabbitListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildOperationBinding(annotation, resolver, context);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildChannelBinding(annotation, resolver, context);
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(Method method) {
        // Currently there is no interesting data in the RabbitListener annotation, but we keep it for the sake of
        // consistency in the code and in the serialized specification (always have at least an empty binding for amqp)
        return RabbitListenerUtil.buildMessageBinding();
    }

    @Override
    protected AsyncHeaders buildHeaders(Method method) {
        return new AsyncHeaders("SpringRabbitListenerDefaultHeaders");
    }
}
