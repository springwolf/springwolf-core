// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StandaloneEnvironmentLoaderTest {

    @Test
    void shouldLoadDefaultEnvironmentTest() {
        // when
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load();

        // then
        assertThat(environment.getActiveProfiles()).containsExactly();
        assertThat(environment.getProperty("spring.application.name")).isEqualTo("Springwolf-core");
    }

    @Test
    void shouldLoadStandaloneProfileIncludingPropertyFile() {
        // when
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load(List.of("standalone"));

        // then
        assertThat(environment.getActiveProfiles()).containsExactly("standalone");
        assertThat(environment.getProperty("spring.application.name")).isEqualTo("Springwolf-core-properties");
    }
}
