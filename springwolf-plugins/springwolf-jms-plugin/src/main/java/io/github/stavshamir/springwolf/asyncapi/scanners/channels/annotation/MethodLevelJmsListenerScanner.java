// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.channels.annotation;

import com.asyncapi.v2.binding.channel.ChannelBinding;
import com.asyncapi.v2.binding.message.MessageBinding;
import com.asyncapi.v2.binding.operation.OperationBinding;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.ChannelsScanner;
import io.github.stavshamir.springwolf.asyncapi.scanners.classes.ComponentClassScanner;
import io.github.stavshamir.springwolf.schemas.SchemasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class MethodLevelJmsListenerScanner extends AbstractMethodLevelListenerScanner<JmsListener>
        implements ChannelsScanner, EmbeddedValueResolverAware {

    private StringValueResolver resolver;

    public MethodLevelJmsListenerScanner(ComponentClassScanner componentClassScanner, SchemasService schemasService) {
        super(componentClassScanner, schemasService);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected Class<JmsListener> getListenerAnnotationClass() {
        return JmsListener.class;
    }

    @Override
    protected String getChannelName(JmsListener annotation) {
        return JmsListenerUtil.getChannelName(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends ChannelBinding> buildChannelBinding(JmsListener annotation) {
        return JmsListenerUtil.buildChannelBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends OperationBinding> buildOperationBinding(JmsListener annotation) {
        return JmsListenerUtil.buildOperationBinding(annotation, resolver);
    }

    @Override
    protected Map<String, ? extends MessageBinding> buildMessageBinding(JmsListener annotation) {
        return JmsListenerUtil.buildMessageBinding();
    }

    protected Class<?> getPayloadType(Method method) {
        return SpringPayloadAnnotationTypeExtractor.getPayloadType(method);
    }
}
