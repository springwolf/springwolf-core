// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.payload.SpringPayloadAnnotationTypeExtractor;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class MethodLevelKafkaListenerScanner extends AbstractMethodLevelListenerScanner<KafkaListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    private final SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor;

    public MethodLevelKafkaListenerScanner(
            ComponentClassScanner componentClassScanner,
            SchemasService schemasService,
            SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor) {
        super(componentClassScanner, schemasService);
        this.springPayloadAnnotationTypeExtractor = springPayloadAnnotationTypeExtractor;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<KafkaListener> getListenerAnnotationClass() {
        return KafkaListener.class;
    }

    @Override
    protected String getChannelName(KafkaListener annotation) {
        return KafkaListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildChannelBinding();
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildOperationBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(KafkaListener annotation) {
        // Currently there is no interesting data in the KafkaListener annotation, but we keep it for the sake of
        // consistency in the code and in the serialized specification (always have at least an empty binding for kafka)
        return KafkaListenerUtil.buildMessageBinding();
    }

    @Override
    protected Class<?> getPayloadType(Method method) {
        return springPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
