// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes;

import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.core.asyncapi.scanners.beans.DefaultBeanMethodsScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ComponentClassScanner;
import io.github.springwolf.core.asyncapi.scanners.classes.spring.ConfigurationClassScanner;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(
        classes = {
            SpringwolfClassScanner.class,
            ConfigurationClassScanner.class,
            ComponentClassScanner.class,
            DefaultBeanMethodsScanner.class,
            TestComponent.class,
            TestConditionalComponent.class,
            TestOtherConditionalComponent.class
        })
class SpringwolfClassScannerIntegrationTest {
    @MockitoBean
    private AsyncApiDocketService asyncApiDocketService;

    @Autowired
    private SpringwolfClassScanner springwolfClassScanner;

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

        Set<Class<?>> components = springwolfClassScanner.scan();

        assertThat(components)
                .contains(TestBeanConfiguration.TestBean.class)
                .contains(TestComponent.class)
                .doesNotContain(TestConditionalComponent.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }

    @Test
    void getComponentsIncludesConditional() {
        StandardEnvironment environment = (StandardEnvironment) beanFactory.getBean(Environment.class);
        environment.getSystemProperties().put(TestConditionalComponent.CONDITIONAL_PROPERTY, true);

        Set<Class<?>> components = springwolfClassScanner.scan();

        assertThat(components)
                .contains(TestBeanConfiguration.TestBean.class)
                .contains(TestComponent.class)
                .contains(TestConditionalComponent.class)
                .doesNotContain(TestOtherConditionalComponent.class);
    }
}
