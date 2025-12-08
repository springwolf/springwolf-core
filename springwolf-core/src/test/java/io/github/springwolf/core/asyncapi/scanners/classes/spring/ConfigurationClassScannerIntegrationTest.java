// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.asyncapi.scanners.classes.spring;

import io.github.springwolf.asyncapi.v3.model.info.Info;
import io.github.springwolf.core.asyncapi.scanners.classes.TestBeanConfiguration;
import io.github.springwolf.core.configuration.docket.AsyncApiDocket;
import io.github.springwolf.core.configuration.docket.AsyncApiDocketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringJUnitConfig(classes = {ConfigurationClassScanner.class, ComponentClassScanner.class})
class ConfigurationClassScannerIntegrationTest {

    @MockitoBean
    private AsyncApiDocketService asyncApiDocketService;

    @Autowired
    private ConfigurationClassScanner configurationClassScanner;

    @Test
    void getComponents() {
        when(asyncApiDocketService.getAsyncApiDocket())
                .thenReturn(AsyncApiDocket.builder()
                        .info(Info.builder()
                                .title("ConfigurationClassScannerTest-title")
                                .version("ConfigurationClassScannerTest-version")
                                .build())
                        .basePackage("io.github.springwolf.core.asyncapi.scanners.classes")
                        .build());

        Set<Class<?>> configurationClasses = configurationClassScanner.scan();

        assertThat(configurationClasses).containsExactlyInAnyOrder(TestBeanConfiguration.class);
    }
}
