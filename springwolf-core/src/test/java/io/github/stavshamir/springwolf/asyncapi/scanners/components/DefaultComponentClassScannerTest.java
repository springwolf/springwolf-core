package io.github.stavshamir.springwolf.asyncapi.scanners.components;

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
@ContextConfiguration(classes = {DefaultComponentClassScanner.class})
public class DefaultComponentClassScannerTest {

    @MockBean
    private AsyncApiDocket docket;

    @Autowired
    private DefaultComponentClassScanner componentsScanner;

    @Test
    public void getComponents() {
        when(docket.getBasePackage())
                .thenReturn(this.getClass().getPackage().getName());

        Set<Class<?>> components = componentsScanner.scanForComponents();

        assertThat(components)
                .contains(DefaultComponentClassScanner.class)
                .doesNotContain(ComponentClassScanner.class);
    }

}
