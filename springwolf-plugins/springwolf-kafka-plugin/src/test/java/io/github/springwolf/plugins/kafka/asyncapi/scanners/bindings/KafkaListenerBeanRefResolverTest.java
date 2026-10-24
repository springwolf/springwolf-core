// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KafkaListenerBeanRefResolverTest {

    private final StringValueResolver fallbackStringValueResolver = mock(StringValueResolver.class);
    private final KafkaListenerBeanRefResolver resolver = new KafkaListenerBeanRefResolver(fallbackStringValueResolver);
    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void resolveListenerBean() throws NoSuchMethodException {
        // given
        refreshContext(ListenerWithDefaultBeanRef.class);
        KafkaListener annotation = getAnnotation(ListenerWithDefaultBeanRef.class);

        // when
        StringValueResolver stringValueResolver = resolver.getStringValueResolver(
                BindingContext.ofAnnotatedClass(ListenerWithDefaultBeanRef.class, annotation));

        // then
        assertThat(stringValueResolver.resolveStringValue("#{__listener.topic}"))
                .isEqualTo("topic-from-bean");
    }

    @Test
    void resolveCustomBeanRef() throws NoSuchMethodException {
        // given
        refreshContext(ListenerWithCustomBeanRef.class);
        KafkaListener annotation = getAnnotation(ListenerWithCustomBeanRef.class);

        // when
        StringValueResolver stringValueResolver = resolver.getStringValueResolver(
                BindingContext.ofAnnotatedClass(ListenerWithCustomBeanRef.class, annotation));

        // then
        assertThat(stringValueResolver.resolveStringValue("#{myListener.topic}"))
                .isEqualTo("topic-from-custom-bean-ref");
    }

    @Test
    void resolvePlaceholdersAndExpressions() throws NoSuchMethodException {
        // given
        refreshContext(ListenerWithDefaultBeanRef.class);
        KafkaListener annotation = getAnnotation(ListenerWithDefaultBeanRef.class);

        // when
        StringValueResolver stringValueResolver = resolver.getStringValueResolver(
                BindingContext.ofAnnotatedClass(ListenerWithDefaultBeanRef.class, annotation));

        // then
        assertThat(stringValueResolver.resolveStringValue("topic")).isEqualTo("topic");
        assertThat(stringValueResolver.resolveStringValue("${topic.property}")).isEqualTo("topic-from-property");
        assertThat(stringValueResolver.resolveStringValue("#{'${topic.property}'.toUpperCase()}"))
                .isEqualTo("TOPIC-FROM-PROPERTY");
    }

    @Test
    void failWithHintWhenListenerBeanIsNotUnique() throws NoSuchMethodException {
        // given
        refreshContext(ListenerWithDefaultBeanRef.class, ListenerWithDefaultBeanRef.class);
        KafkaListener annotation = getAnnotation(ListenerWithDefaultBeanRef.class);
        StringValueResolver stringValueResolver = resolver.getStringValueResolver(
                BindingContext.ofAnnotatedClass(ListenerWithDefaultBeanRef.class, annotation));

        // when + then
        assertThatThrownBy(() -> stringValueResolver.resolveStringValue("#{__listener.topic}"))
                .hasStackTraceContaining("Unable to resolve '__listener' of @KafkaListener in "
                        + ListenerWithDefaultBeanRef.class.getName())
                .rootCause()
                .isInstanceOf(NoUniqueBeanDefinitionException.class);
    }

    @Test
    void useFallbackWithoutBeanFactory() throws NoSuchMethodException {
        // given
        KafkaListener annotation = getAnnotation(ListenerWithDefaultBeanRef.class);
        when(fallbackStringValueResolver.resolveStringValue("${topic.property}"))
                .thenReturn("topic-from-fallback");

        // when
        StringValueResolver stringValueResolver = resolver.getStringValueResolver(
                BindingContext.ofAnnotatedClass(ListenerWithDefaultBeanRef.class, annotation));

        // then
        assertThat(stringValueResolver.resolveStringValue("${topic.property}")).isEqualTo("topic-from-fallback");
    }

    private void refreshContext(Class<?>... listenerClasses) {
        context.getEnvironment()
                .getPropertySources()
                .addFirst(new MapPropertySource("test", Map.of("topic.property", "topic-from-property")));
        for (int i = 0; i < listenerClasses.length; i++) {
            context.registerBean("listener" + i, listenerClasses[i]);
        }
        context.refresh();
        resolver.setBeanFactory(context.getBeanFactory());
    }

    private static KafkaListener getAnnotation(Class<?> listenerClass) throws NoSuchMethodException {
        return listenerClass.getDeclaredMethod("listen", String.class).getAnnotation(KafkaListener.class);
    }

    public static class ListenerWithDefaultBeanRef {
        public String getTopic() {
            return "topic-from-bean";
        }

        @KafkaListener(topics = "#{__listener.topic}")
        public void listen(String payload) {}
    }

    public static class ListenerWithCustomBeanRef {
        public String getTopic() {
            return "topic-from-custom-bean-ref";
        }

        @KafkaListener(topics = "#{myListener.topic}", beanRef = "myListener")
        public void listen(String payload) {}
    }
}
