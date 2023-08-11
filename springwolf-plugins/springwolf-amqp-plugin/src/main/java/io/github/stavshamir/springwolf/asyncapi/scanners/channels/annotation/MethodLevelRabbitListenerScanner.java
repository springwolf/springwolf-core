package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.github.stavshamir.springwolf.configuration.properties.SpringwolfAmqpConfigConstants.SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED;

@Slf4j
@Service
@Order(value = ChannelPriority.AUTO_DISCOVERED)
@ConditionalOnProperty(name = SPRINGWOLF_SCANNER_RABBIT_LISTENER_ENABLED, matchIfMissing = true)
public class MethodLevelRabbitListenerScanner extends AbstractMethodLevelListenerScanner<RabbitListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private final RabbitListenerUtil.RabbitListenerUtilContext context;
    private StringValueResolver resolver;

    public MethodLevelRabbitListenerScanner(List<Queue> queues, List<Exchange> exchanges, List<Binding> bindings) {
        Map<String, Queue> queueMap =
                queues.stream().collect(Collectors.toMap(Queue::getName, Function.identity(), (e1, e2) -> e1));
        Map<String, Exchange> exchangeMap =
                exchanges.stream().collect(Collectors.toMap(Exchange::getName, Function.identity(), (e1, e2) -> e1));
        Map<String, Binding> bindingMap = bindings.stream()
                .filter(Binding::isDestinationQueue)
                .collect(Collectors.toMap(Binding::getDestination, Function.identity(), (e1, e2) -> e1));
        context = new RabbitListenerUtil.RabbitListenerUtilContext(queueMap, exchangeMap, bindingMap);
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
    protected String getChannelName(RabbitListener annotation) {
        return RabbitListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildChannelBinding(annotation, resolver, context);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildOperationBinding(annotation, resolver, context);
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(RabbitListener annotation) {
        return RabbitListenerUtil.buildMessageBinding();
    }

    protected Class<?> getPayloadType(Method method) {
        return SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
