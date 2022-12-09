package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.ChannelBinding;
import com.asyncapi.v2.binding.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelPriority;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeaders;
import io.github.stavshamir.springwolf.asyncapi.types.channel.operation.message.header.AsyncHeadersForSpringKafkaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

import static io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation.SpringPayloadAnnotationTypeExtractor.getPayloadType;

@Slf4j
@Service
@RequiredArgsConstructor
@Order(value = ChannelPriority.AUTO_DISCOVERED)
public class ClassLevelKafkaListenerScanner extends AbstractClassLevelListenerScanner<KafkaListener, KafkaHandler>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<KafkaListener> getListenerAnnotationClass() {
        return KafkaListener.class;
    }

    @Override
    protected Class<KafkaHandler> getHandlerAnnotationClass() {
        return KafkaHandler.class;
    }

    @Override
    protected String getChannelName(KafkaListener annotation) {
        return KafkaListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildOperationBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildChannelBinding();
    }

    @Override
    protected AsyncHeaders buildHeaders(Method method) {
        Class<?> payloadType = getPayloadType(method);
        return new AsyncHeadersForSpringKafkaBuilder("SpringKafkaDefaultHeaders-" + payloadType.getSimpleName())
                .withTypeIdHeader(payloadType.getTypeName())
                .build();
    }

}
