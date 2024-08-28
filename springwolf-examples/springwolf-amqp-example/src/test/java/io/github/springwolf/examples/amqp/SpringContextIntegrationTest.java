// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import io.github.springwolf.core.asyncapi.AsyncApiService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpringContextIntegrationTest {

    @Nested
    @SpringBootTest(classes = SpringwolfAmqpExampleApplication.class)
    @TestPropertySource(
            properties = {
                "springwolf.enabled=true",
                "springwolf.docket.info.title=Info title was loaded from spring properties",
                "springwolf.docket.info.version=1.0.0",
                "springwolf.docket.base-package=io.github.springwolf.examples",
                "springwolf.docket.servers.test-protocol.protocol=amqp",
                "springwolf.docket.servers.test-protocol.host=some-server:1234",
            })
    @ActiveProfiles("test")
    class ApplicationPropertiesConfigurationTest {

        @Autowired
        private ApplicationContext context;

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void testContextWithApplicationProperties() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }

        @Test
        void testAllChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(11);
        }
    }

    @Nested
    @SpringBootTest(classes = SpringwolfAmqpExampleApplication.class)
    @TestPropertySource(
            properties = {
                "springwolf.scanner.async-listener.enabled=false",
                "springwolf.scanner.async-publisher.enabled=false",
                "springwolf.scanner.consumer-data.enabled=false",
                "springwolf.scanner.producer-data.enabled=false",
                "springwolf.plugin.amqp.scanner.rabbit-listener.enabled=false",
            })
    @ActiveProfiles("test")
    class DisabledScannerTest {

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        void testNoChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).isEmpty();
        }
    }
}
