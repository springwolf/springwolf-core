package io.github.stavshamir.springwolf.example;

import io.github.stavshamir.springwolf.asyncapi.AsyncApiService;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;


@RunWith(Enclosed.class)
public class SpringContextTest {

    @RunWith(SpringRunner.class)
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
    }

    @RunWith(SpringRunner.class)
    @SpringBootTest(classes = SpringwolfExampleApplication.class)
    @TestPropertySource(properties = {
            "customAsyncApiDocketBean=false",
            "springwolf.enabled=true",
            "springwolf.docket.info.title=Info title was loaded from spring properties",
            "springwolf.docket.info.version=1.0.0",
            "springwolf.docket.base-package=io.github.stavshamir.springwolf.example.consumers",
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
    }
}