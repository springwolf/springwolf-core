package io.github.stavshamir.springwolf.asyncapi.scanners.components;

import io.github.stavshamir.springwolf.asyncapi.scanners.components.CustomConfiguration.ClassCreatedFromFactory;
import io.github.stavshamir.springwolf.asyncapi.scanners.components.CustomConfiguration.ClassCreationFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        CustomConfiguration.class
})
public class ApplicationContextComponentsScannerTest {

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
