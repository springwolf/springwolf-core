package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.stavshamir.springwolf.asyncapi.scanners.components.ApplicationContextComponentsScannerTest.CustomConfiguration.ClassCreatedFromFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.ApplicationContextComponentsScannerTest.CustomConfiguration.ClassCreationFactory;
import java.util.Set;
import lombok.Value;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
    ApplicationContextComponentsScannerTest.CustomConfiguration.class
})
public class ApplicationContextComponentsScannerTest {

    @TestConfiguration
    @Import(TestBeanConfiguration.class)
    public static class CustomConfiguration {

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

    @Autowired
    private ComponentsScanner componentsScanner;

    @Test
    public void scanForComponents_should_find_component_configuration_beanfactory() {

        Set<Class<?>> classes = this.componentsScanner.scanForComponents();

        assertThat(classes)
            .contains(ApplicationContextComponentsScanner.class)
            .contains(TestBean.class)
            .contains(ClassCreatedFromFactory.class)
            .doesNotContain(ClassCreationFactory.class);
    }
}
