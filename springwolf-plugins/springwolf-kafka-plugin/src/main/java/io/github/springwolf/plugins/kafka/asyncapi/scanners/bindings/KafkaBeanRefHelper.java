// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.core.asyncapi.scanners.bindings.common.BindingContext;
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

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public class KafkaBeanRefHelper implements ApplicationContextAware {
    private final StringValueResolver defaultStringValueResolver;
    private BeanFactory beanFactory;

    public StringValueResolver getStringValueResolver(
            KafkaListener annotation, @Nullable BindingContext bindingContext) {
        if (bindingContext == null) {
            return defaultStringValueResolver;
        }

        ListenerScope listenerScope =
                new ListenerScope(annotation.beanRef(), beanFactory.getBean(bindingContext.getClassContext()));
        BeanExpressionContext beanExpressionContext = null;
        BeanExpressionResolver resolver = new StandardBeanExpressionResolver();
        if (beanFactory instanceof ConfigurableListableBeanFactory clbf) {
            BeanExpressionResolver beanExpressionResolver = clbf.getBeanExpressionResolver();
            if (beanExpressionResolver != null) {
                resolver = beanExpressionResolver;
            }
            beanExpressionContext = new BeanExpressionContext(clbf, listenerScope);
        }
        return new ListenerStringValueResolver(beanExpressionContext, resolver);
    }

    private static class ListenerScope implements Scope {

        private final Map<String, Object> listeners;

        public ListenerScope(String listenerBeanRef, Object listener) {
            listeners = Collections.singletonMap(listenerBeanRef, listener);
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

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof ConfigurableApplicationContext cac) {
            this.beanFactory = cac.getBeanFactory();
        } else {
            this.beanFactory = applicationContext;
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
}
