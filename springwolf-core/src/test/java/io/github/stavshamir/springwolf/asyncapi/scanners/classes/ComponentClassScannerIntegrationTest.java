// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import com.asyncapi.v2._6_0.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ComponentClassScanner.class})
@DirtiesContext
class ComponentClassScannerIntegrationTest {
    @MockBean
    private AsyncApiDocketService asyncApiDocketService;

    @Autowired
    private ComponentClassScanner componentsScanner;

    @Autowired
    ConfigurableBeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        when(asyncApiDocketService.getAsyncApiDocket())
                .thenReturn(AsyncApiDocket.builder()
                        .info(Info.builder()
                                .title("ComponentClassScannerTest-title")
                                .version("ComponentClassScannerTest-version")
                                .build())
                        .basePackage(this.getClass().getPackage().getName())
                        .build());
    }

    @Test
    void getComponents() {
        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .contains(ComponentClassScanner.class)
                .contains(ConfigurationClassScanner.class)
                .doesNotContain(ClassScanner.class)
                .doesNotContain(TestConditionalComponent.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }

    @Test
    void getComponentsIncludesConditional() {
        StandardEnvironment environment = (StandardEnvironment) beanFactory.getBean(Environment.class);
        environment.getSystemProperties().put(TestConditionalComponent.CONDITIONAL_PROPERTY, true);

        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .contains(ComponentClassScanner.class)
                .contains(ConfigurationClassScanner.class)
                .contains(TestConditionalComponent.class)
                .doesNotContain(ClassScanner.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }
}
