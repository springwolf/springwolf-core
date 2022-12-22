package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import com.asyncapi.v2.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = {ConfigurationClassScanner.class})
public class ConfigurationClassScannerTest {

    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

    @Autowired
    private ConfigurationClassScanner configurationClassScanner;

    @Test
    public void getComponents() {
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(
                AsyncApiDocket.builder()
                        .info(Info.builder()
                                .title("ConfigurationClassScannerTest-title")
                                .version("ConfigurationClassScannerTest-version")
                                .build())
                        .basePackage(this.getClass().getPackage().getName())
                        .build()
        );

        Set<Class<?>> configurationClasses = configurationClassScanner.scan();

        assertThat(configurationClasses)
                .containsExactlyInAnyOrder(TestBeanConfiguration.class);
    }

}
