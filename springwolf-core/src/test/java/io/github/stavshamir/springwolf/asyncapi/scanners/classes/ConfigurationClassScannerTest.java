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
@ContextConfiguration(classes = {ConfigurationClassScanner.class})
public class ConfigurationClassScannerTest {

    @MockBean
    private AsyncApiDocket docket;

    @Autowired
    private ConfigurationClassScanner configurationClassScanner;

    @Test
    public void getComponents() {
        when(docket.getBasePackage())
                .thenReturn(this.getClass().getPackage().getName());

        Set<Class<?>> configurationClasses = configurationClassScanner.scan();

        assertThat(configurationClasses)
                .containsExactlyInAnyOrder(TestBeanConfiguration.class);
    }

}
