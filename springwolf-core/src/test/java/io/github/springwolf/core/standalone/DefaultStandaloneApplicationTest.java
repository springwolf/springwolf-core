// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.integrationtests.application.configuration.CustomizerMarkerConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatRuntimeException;

class DefaultStandaloneApplicationTest {

    @Test
    void shouldCreateStandaloneFactoryWithBasePackageFromProperties() {
        // when
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load();
        environment
                .getPropertySources()
                .addFirst(new MapPropertySource(
                        "env",
                        Map.of(
                                "springwolf.docket.base-package",
                                "io.github.springwolf.core.integrationtests.application.listener")));
        DefaultStandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .setEnvironment(environment)
                .buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(standaloneApplication
                        .getAsyncApiService()
                        .getAsyncAPI()
                        .getInfo()
                        .getTitle())
                .isEqualTo("springwolf-core-test-properties-file");
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI().getChannels())
                .containsKey("listener-channel");
    }

    @Test
    void shouldCreateStandaloneFactoryWithScanPackage() {
        // when
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load(List.of("standalone"));
        environment
                .getPropertySources()
                .addFirst(new MapPropertySource(
                        "env", Map.of("springwolf.docket.base-package", "none.existing.package")));

        CustomizerMarkerConfiguration.hasCustomized = false;
        DefaultStandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .addScanPackage(CustomizerMarkerConfiguration.class.getPackageName())
                .setEnvironment(environment)
                .buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(CustomizerMarkerConfiguration.hasCustomized).isTrue();
    }

    @Test
    void shouldCreateStandaloneFactoryForBasicApplicationWithProfile() {
        // given
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load(List.of("standalone", "base-package"));

        // when
        DefaultStandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .setEnvironment(environment)
                .buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(standaloneApplication
                        .getAsyncApiService()
                        .getAsyncAPI()
                        .getInfo()
                        .getTitle())
                .isEqualTo("springwolf-core-standalone");
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI().getChannels())
                .containsKey("listener-channel");
    }

    @Test
    void shouldThrowWhenConfigurationsAreMissing() {
        // when
        List<String> scanPackages = List.of("non.existing.package");

        // then
        assertThatRuntimeException().isThrownBy(() -> DefaultStandaloneApplication.builder()
                .setScanPackages(scanPackages)
                .buildAndStart());
    }

    @Test
    void shouldThrowWhenNoBasePackageConfigured() {
        // when
        DefaultStandaloneApplication standaloneApplication =
                DefaultStandaloneApplication.builder().buildAndStart();

        // then
        assertThatRuntimeException()
                .isThrownBy(() -> standaloneApplication.getAsyncApiService().getAsyncAPI());
    }
}
