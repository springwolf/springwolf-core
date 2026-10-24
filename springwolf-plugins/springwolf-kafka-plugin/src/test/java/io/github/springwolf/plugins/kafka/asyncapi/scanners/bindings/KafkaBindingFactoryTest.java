// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.kafka.asyncapi.scanners.bindings;

import io.github.springwolf.asyncapi.v3.bindings.OperationBinding;
import io.github.springwolf.asyncapi.v3.bindings.kafka.KafkaOperationBinding;
import io.github.springwolf.asyncapi.v3.model.schema.SchemaObject;
import io.github.springwolf.core.asyncapi.scanners.bindings.BindingContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Method;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaBindingFactoryTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    private KafkaBindingFactory kafkaBindingFactory;

    @BeforeEach
    void setUp() {
        context.getEnvironment()
                .getPropertySources()
                .addFirst(new MapPropertySource("test", Map.of("topic.property", "topic-from-property")));
        context.registerBean(MethodLevelListener.class);
        context.registerBean(ClassLevelListener.class);
        context.refresh();

        StringValueResolver stringValueResolver = context.getBeanFactory()::resolveEmbeddedValue;
        KafkaListenerBeanRefResolver beanRefResolver = new KafkaListenerBeanRefResolver(stringValueResolver);
        beanRefResolver.setBeanFactory(context.getBeanFactory());
        kafkaBindingFactory = new KafkaBindingFactory(stringValueResolver, beanRefResolver);
    }

    @AfterEach
    void tearDown() {
        context.close();
    }

    @Test
    void methodLevelListenerWithBeanRef() throws NoSuchMethodException {
        // given
        Method method = MethodLevelListener.class.getDeclaredMethod("listen", String.class);
        KafkaListener annotation = method.getAnnotation(KafkaListener.class);
        BindingContext<KafkaListener> bindingContext =
                BindingContext.ofAnnotatedMethod(MethodLevelListener.class, method, annotation);

        // when
        String channelName = kafkaBindingFactory.getChannelName(bindingContext);
        String channelId = kafkaBindingFactory.getChannelId(bindingContext);
        Map<String, OperationBinding> operationBinding = kafkaBindingFactory.buildOperationBinding(bindingContext);

        // then
        assertThat(channelName).isEqualTo("topic-from-bean");
        assertThat(channelId).isEqualTo("topic-from-bean");
        assertThat(getGroupId(operationBinding)).isEqualTo("group-from-bean");
    }

    @Test
    void classLevelListenerWithBeanRef() {
        // given
        KafkaListener annotation = ClassLevelListener.class.getAnnotation(KafkaListener.class);
        BindingContext<KafkaListener> bindingContext =
                BindingContext.ofAnnotatedClass(ClassLevelListener.class, annotation);

        // when
        String channelName = kafkaBindingFactory.getChannelName(bindingContext);
        Map<String, OperationBinding> operationBinding = kafkaBindingFactory.buildOperationBinding(bindingContext);

        // then
        assertThat(channelName).isEqualTo("topic-from-property");
        assertThat(getGroupId(operationBinding)).isEqualTo("class-level-group-from-bean");
    }

    private static Object getGroupId(Map<String, OperationBinding> operationBinding) {
        KafkaOperationBinding kafkaOperationBinding = (KafkaOperationBinding) operationBinding.get("kafka");
        return ((SchemaObject) kafkaOperationBinding.getGroupId()).getConstValue();
    }

    public static class MethodLevelListener {
        public String getTopic() {
            return "topic-from-bean";
        }

        public String getGroup() {
            return "group-from-bean";
        }

        @KafkaListener(topics = "#{__listener.topic}", groupId = "#{__listener.group}")
        public void listen(String payload) {}
    }

    @KafkaListener(topics = "${topic.property}", groupId = "#{__listener.group}")
    public static class ClassLevelListener {
        public String getGroup() {
            return "class-level-group-from-bean";
        }

        @KafkaHandler
        public void listen(String payload) {}
    }
}
