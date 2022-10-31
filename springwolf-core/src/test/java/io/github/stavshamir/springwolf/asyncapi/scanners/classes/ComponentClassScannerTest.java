package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ComponentClassScanner.class})
public class ComponentClassScannerTest {

    @MockBean
    private AsyncApiDocket docket;

    @Autowired
    private ComponentClassScanner componentsScanner;

    @Test
    public void getComponents() {
        when(docket.getBasePackage())
                .thenReturn(this.getClass().getPackage().getName());

        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .contains(ComponentClassScanner.class)
                .contains(ConfigurationClassScanner.class)
                .doesNotContain(ClassScanner.class);
    }

}
