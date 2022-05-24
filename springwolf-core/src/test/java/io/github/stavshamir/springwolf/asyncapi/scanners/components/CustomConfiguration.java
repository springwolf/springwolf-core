package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import lombok.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(TestBeanConfiguration.class)
public class CustomConfiguration {

    @Bean
    public ComponentsScanner beansScanner() {
        return new ApplicationContextComponentsScanner(it -> true);
    }

    @Bean
    public ClassCreationFactory classCreationFactory() {
        return new ClassCreationFactory();
    }

    public static class ClassCreationFactory extends AbstractFactoryBean<ClassCreatedFromFactory> {

        @Override
        public Class<?> getObjectType() {
            return ClassCreatedFromFactory.class;
        }

        @Override
        protected ClassCreatedFromFactory createInstance() {
            return new ClassCreatedFromFactory("abc");
        }
    }

    @Value
    public static class ClassCreatedFromFactory {
        String value;
    }
}
