// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.core.standalone;

import io.github.springwolf.core.integrationtests.application.basic.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

class DefaultStandaloneFactoryTest {

    private String applicationPackageName = TestApplication.class.getPackageName();

    @Test
    void shouldCreateStandaloneFactoryForBasicApplication() {
        // when
        DefaultStandaloneFactory factory = new DefaultStandaloneFactory(TestApplication.class.getPackageName());

        // then
        assertThat(factory.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(factory.getAsyncApiService().getAsyncAPI().getInfo().getTitle())
                .isEqualTo("Springwolf-core");
    }

    @Test
    void shouldCreateStandaloneFactoryForBasicApplicationWithProfile() {
        // given
        ConfigurableEnvironment environment = StandaloneEnvironmentLoader.load(List.of("standalone"));

        // when
        DefaultStandaloneFactory factory =
                new DefaultStandaloneFactory(TestApplication.class.getPackageName(), environment);

        // then
        assertThat(factory.getAsyncApiService().getAsyncAPI()).isNotNull();
        assertThat(factory.getAsyncApiService().getAsyncAPI().getInfo().getTitle())
                .isEqualTo("Springwolf-core-properties");
    }

    @Test
    void shouldThrowWhenConfigurationsAreMissing() {
        // when
        List<Class<?>> standaloneConfigurations = List.of();

        try {
            new DefaultStandaloneFactory(applicationPackageName, new StandardEnvironment(), standaloneConfigurations);
            fail();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NoSuchBeanDefinitionException.class);
        }
    }
}
