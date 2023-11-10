// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class MethodLevelSqsListenerScanner extends AbstractMethodLevelListenerScanner<SqsListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    private final SpringPayloadAnnotationTypeExtractor springPayloadAnnotationTypeExtractor;

    public MethodLevelSqsListenerScanner(
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
        return springPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
