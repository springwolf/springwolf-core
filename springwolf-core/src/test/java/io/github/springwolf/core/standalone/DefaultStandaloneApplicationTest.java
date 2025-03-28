// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

class DefaultStandaloneApplicationTest {

    @Test
    void shouldCreateStandaloneFactoryForBasicApplicationWithBasePackeFromProperties() {
        // when
        DefaultStandaloneApplication standaloneApplication =
                DefaultStandaloneApplication.builder().buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(standaloneApplication
                        .getAsyncApiService()
                        .getAsyncAPI()
                        .getInfo()
                        .getTitle())
                .isEqualTo("Springwolf-core");
    }

    @Test
    void shouldCreateStandaloneFactoryForBasicApplication() {
        // when
        DefaultStandaloneApplication standaloneApplication = DefaultStandaloneApplication.builder()
                .addScanPackage(TestApplication.class.getPackageName())
                .buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(standaloneApplication
                        .getAsyncApiService()
                        .getAsyncAPI()
                        .getInfo()
                        .getTitle())
                .isEqualTo("Springwolf-core");
    }

    @Test
    void shouldCreateStandaloneFactoryForBasicApplicationWithProfile() {
        // given
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load(List.of("standalone"));

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
                .isEqualTo("Springwolf-core-properties");
    }

    @Test
    void shouldThrowWhenConfigurationsAreMissing() {
        // when
        List<String> scanPackages = List.of("non.existing.package");

        try {
            DefaultStandaloneApplication.builder().setScanPackages(scanPackages).buildAndStart();
            fail();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalStateException.class);
        }
    }

    @Test
    void shouldThrowWhenNoBasePackageConfigured() {
        // when
        DefaultStandaloneApplication standaloneApplication =
                DefaultStandaloneApplication.builder().buildAndStart();

        // then
        assertThat(standaloneApplication.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(standaloneApplication
                        .getAsyncApiService()
                        .getAsyncAPI()
                        .getInfo()
                        .getTitle())
                .isEqualTo("Springwolf-core");
    }
}
