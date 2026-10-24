// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

/**
 * Resolves placeholders and SpEL expressions of {@link KafkaListener} attributes,
 * including the pseudo bean {@link KafkaListener#beanRef()} (default: {@code __listener}),
 * which references the bean declaring the listener, e.g. {@code @KafkaListener(topics = "#{__listener.topic}")}.
 * <p>
 * Lightweight variant of the {@code ListenerScope} used in spring-kafka's
 * {@link org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor}.
 * <p>
 * Springwolf scans classes, not bean instances. Therefore, the bean is looked up by the type of the listener class,
 * which requires exactly one bean of that type.
 */
@RequiredArgsConstructor
public class KafkaListenerBeanRefResolver implements BeanFactoryAware {
    private final StringValueResolver fallbackStringValueResolver;

    @Nullable
    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        if (beanFactory instanceof ConfigurableBeanFactory configurableBeanFactory) {
            this.beanFactory = configurableBeanFactory;
        }
    }

    public StringValueResolver getStringValueResolver(KafkaListener annotation, BindingContext bindingContext) {
        if (beanFactory == null || beanFactory.getBeanExpressionResolver() == null) {
            return fallbackStringValueResolver;
        }

        ConfigurableBeanFactory factory = beanFactory;
        BeanExpressionResolver expressionResolver = factory.getBeanExpressionResolver();
        BeanExpressionContext expressionContext = new BeanExpressionContext(
                factory, new BeanRefScope(factory, annotation.beanRef(), bindingContext.annotatedClass()));

        // same as org.springframework.beans.factory.config.EmbeddedValueResolver, but with the listener scope
        return value -> {
            String resolved = factory.resolveEmbeddedValue(value);
            if (resolved == null) {
                return null;
            }
            Object evaluated = expressionResolver.evaluate(resolved, expressionContext);
            return evaluated != null ? evaluated.toString() : null;
        };
    }

    private record BeanRefScope(BeanFactory beanFactory, String beanRef, Class<?> listenerClass) implements Scope {
        @Override
        @Nullable
        public Object resolveContextualObject(String key) {
            if (!beanRef.equals(key)) {
                return null;
            }

            try {
                return beanFactory.getBean(listenerClass);
            } catch (BeansException ex) {
                throw new IllegalStateException(
                        String.format(
                                "Unable to resolve '%s' of @KafkaListener in %s. "
                                        + "Springwolf looks up the listener bean by its type, "
                                        + "which requires exactly one bean of type %s",
                                beanRef, listenerClass.getName(), listenerClass.getName()),
                        ex);
            }
        }

        @Override
        public Object get(String name, ObjectFactory<?> objectFactory) {
            return objectFactory.getObject();
        }

        @Override
        @Nullable
        public Object remove(String name) {
            return null;
        }

        @Override
        public void registerDestructionCallback(String name, Runnable callback) {}

        @Override
        @Nullable
        public String getConversationId() {
            return null;
        }
    }
}
