package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import com.asyncapi.v2._0_0.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ComponentClassScanner.class})
class ComponentClassScannerTest {
    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

    @Autowired
    private ComponentClassScanner componentsScanner;

    @Test
    void getComponents() {
        when(asyncApiDocketService.getAsyncApiDocket()).thenReturn(
                AsyncApiDocket.builder()
                        .info(Info.builder()
                                .title("ComponentClassScannerTest-title")
                                .version("ComponentClassScannerTest-version")
                                .build())
                        .basePackage(this.getClass().getPackage().getName())
                        .build()
        );

        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .contains(ComponentClassScanner.class)
                .contains(ConfigurationClassScanner.class)
                .doesNotContain(ClassScanner.class);
    }

}
