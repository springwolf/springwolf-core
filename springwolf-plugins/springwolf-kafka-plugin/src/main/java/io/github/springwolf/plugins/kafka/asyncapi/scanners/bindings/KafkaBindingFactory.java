// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.ChannelBinding;
import io.github.springwolf.asyncapi.v3.bindings.MessageBinding;
import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingFactory;
import io.github.springwolf.plugins.kafka.asyncapi.scanners.common.KafkaListenerUtil;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class KafkaBindingFactory implements BindingFactory<KafkaListener>, ApplicationContextAware {
    private final ListenerScope listenerScope = new ListenerScope();
    private ListenerStringValueResolver stringValueResolver;
    private ApplicationContext applicationContext;
    private BeanExpressionContext expressionContext;
    private BeanExpressionResolver resolver = new StandardBeanExpressionResolver();

    @Override
    public String getChannelName(KafkaListener annotation, Class<?> component) {
        listenerScope.addListener(annotation.beanRef(), applicationContext.getBean(component));
        String result = KafkaListenerUtil.getChannelName(annotation, stringValueResolver);
        listenerScope.removeListener(annotation.beanRef());
        return result;
    }

    @Override
    public Map<String, ChannelBinding> buildChannelBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildChannelBinding();
    }

    @Override
    public Map<String, OperationBinding> buildOperationBinding(KafkaListener annotation) {
        return KafkaListenerUtil.buildOperationBinding(annotation, stringValueResolver);
    }

    @Override
    public Map<String, MessageBinding> buildMessageBinding(KafkaListener annotation, SchemaObject headerSchema) {
        return KafkaListenerUtil.buildMessageBinding(headerSchema);
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        if (applicationContext instanceof ConfigurableApplicationContext cac) {
            setBeanFactory(cac.getBeanFactory());
        } else {
            setBeanFactory(applicationContext);
        }

        stringValueResolver = new ListenerStringValueResolver(expressionContext, resolver);
    }

    private void setBeanFactory(BeanFactory beanFactory) {
        if (beanFactory instanceof ConfigurableListableBeanFactory clbf) {
            BeanExpressionResolver beanExpressionResolver = clbf.getBeanExpressionResolver();
            if (beanExpressionResolver != null) {
                this.resolver = beanExpressionResolver;
            }
            this.expressionContext = new BeanExpressionContext(clbf, this.listenerScope);
        }
    }

    private record ListenerStringValueResolver(
            BeanExpressionContext beanExpressionContext, BeanExpressionResolver beanExpressionResolver)
            implements StringValueResolver {
        @Override
        @Nullable
        public String resolveStringValue(@Nonnull String strVal) {
            if (beanExpressionContext == null) {
                return strVal;
            }

            Object resolved = beanExpressionResolver.evaluate(strVal, beanExpressionContext);
            return resolved == null ? null : resolved.toString();
        }
    }

    private static class ListenerScope implements Scope {

        private final Map<String, Object> listeners = new HashMap<>();

        ListenerScope() {}

        public void addListener(String key, Object bean) {
            this.listeners.put(key, bean);
        }

        public void removeListener(String key) {
            this.listeners.remove(key);
        }

        @Override
        @Nonnull
        public Object get(@Nonnull String name, @Nonnull ObjectFactory<?> objectFactory) {
            return this.listeners.get(name);
        }

        @Override
        public Object remove(@Nonnull String name) {
            return null;
        }

        @Override
        public void registerDestructionCallback(@Nonnull String name, @Nonnull Runnable callback) {}

        @Override
        public Object resolveContextualObject(@Nonnull String key) {
            return this.listeners.get(key);
        }

        @Override
        public String getConversationId() {
            return null;
        }
    }
}
