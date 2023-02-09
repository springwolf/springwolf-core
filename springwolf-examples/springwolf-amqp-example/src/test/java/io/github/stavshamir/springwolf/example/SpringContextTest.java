package io.github.stavshamir.springwolf.example;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SpringContextTest {

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    public static class AsyncApiDocketTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        public void testContextWithAsyncApiDocketBean() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }

        @Test
        public void testAllChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(7);
        }
    }

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @TestPropertySource(properties = {
            "customAsyncApiDocketBean=false",
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example",
            "springwolf.docket.servers.test-protocol.protocol=amqp",
            "springwolf.docket.servers.test-protocol.url=some-server:1234",
    })
    public static class ApplicationPropertiesConfigurationTest {

        @Autowired
        private ApplicationContext context;
        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        public void testContextWithApplicationProperties() {
            assertNotNull(context);

            assertThat(asyncApiService.getAsyncAPI()).isNotNull();
        }

        @Test
        public void testAllChannelsAreFound() {
            // 2 channels defined in the AsyncDocket are not found (7 - 2 = 5)
            assertThat(asyncApiService.getAsyncAPI().getChannels()).hasSize(5);
        }
    }

    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @TestPropertySource(properties = {
            "springwolf.scanner.async-listener.enabled=false",
            "springwolf.scanner.async-publisher.enabled=false",
            "springwolf.scanner.consumer-data.enabled=false",
            "springwolf.scanner.producer-data.enabled=false",
            "springwolf.plugin.amqp.scanner.rabbit-listener.enabled=false",
    })
    public static class DisabledScannerTest {

        @Autowired
        private AsyncApiService asyncApiService;

        @Test
        public void testNoChannelsAreFound() {
            assertThat(asyncApiService.getAsyncAPI().getChannels()).isEmpty();
        }
    }
}
