// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.configuration.properties.SpringwolfConfigConstants;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StandaloneConfigurationDiscovererTest {
    private final ConfigurableEnvironment environment = new StandardEnvironment();

    @Test
    void shouldFindAllCoreStandaloneConfigurations() {
        // when
        List<String> classes =
                StandaloneConfigurationDiscoverer.scan(SpringwolfConfigConstants.SPRINGWOLF_PACKAGE, environment)
                        .stream()
                        .map(Class::getName)
                        .toList();

        // then
        assertThat(classes)
                .containsExactlyInAnyOrder( //
                        "io.github.springwolf.core.integrationtests.application.configuration.CustomizerMarkerConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfCoreConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfPropertiesConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfScannerConfiguration",
                        "io.github.springwolf.addons.common_model_converters.configuration.CommonModelConvertersAutoConfiguration");
    }

    @Test
    void shouldFindAllConfigurationsInPackage() {
        // when
        List<String> classes =
                StandaloneConfigurationDiscoverer.scan("io.github.springwolf.non.existing.package", environment)
                        .stream()
                        .map(Class::getName)
                        .toList();

        // then
        assertThat(classes).containsExactlyInAnyOrder();
    }

    @Test
    void shouldFindAllCoreConfigurationsInPackage() {
        // when
        List<String> classes = StandaloneConfigurationDiscoverer.scan("io.github.springwolf.core", environment).stream()
                .map(Class::getName)
                .toList();

        // then
        assertThat(classes)
                .containsExactlyInAnyOrder(
                        "io.github.springwolf.core.integrationtests.application.configuration.CustomizerMarkerConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfCoreConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfPropertiesConfiguration",
                        "io.github.springwolf.core.configuration.SpringwolfScannerConfiguration");
    }
}
