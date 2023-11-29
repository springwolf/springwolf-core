// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.asyncapi.scanners.classes;

import com.asyncapi.v2._6_0.model.info.Info;
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
@ContextConfiguration(classes = {ConfigurationClassScanner.class, ComponentClassScanner.class})
class ConfigurationClassScannerIntegrationTest {

    @MockBean
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
                        .basePackage(this.getClass().getPackage().getName())
                        .build());

        Set<Class<?>> configurationClasses = configurationClassScanner.scan();

        assertThat(configurationClasses).containsExactlyInAnyOrder(TestBeanConfiguration.class);
    }
}
