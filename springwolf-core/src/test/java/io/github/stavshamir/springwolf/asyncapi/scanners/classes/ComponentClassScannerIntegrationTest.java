// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import io.github.stavshamir.springwolf.asyncapi.v3.model.info.Info;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(
        classes = {
            ComponentClassScanner.class,
            TestComponent.class,
            TestConditionalComponent.class,
            TestOtherConditionalComponent.class
        })
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
        StandardEnvironment environment = (StandardEnvironment) beanFactory.getBean(Environment.class);
        environment.getSystemProperties().put(TestConditionalComponent.CONDITIONAL_PROPERTY, false);

        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .doesNotContain(TestBeanConfiguration.TestBean.class)
                .contains(TestComponent.class)
                .doesNotContain(TestConditionalComponent.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }

    @Test
    void getComponentsIncludesConditional() {
        StandardEnvironment environment = (StandardEnvironment) beanFactory.getBean(Environment.class);
        environment.getSystemProperties().put(TestConditionalComponent.CONDITIONAL_PROPERTY, true);

        Set<Class<?>> components = componentsScanner.scan();

        assertThat(components)
                .doesNotContain(TestBeanConfiguration.TestBean.class)
                .contains(TestComponent.class)
                .contains(TestConditionalComponent.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }
}
