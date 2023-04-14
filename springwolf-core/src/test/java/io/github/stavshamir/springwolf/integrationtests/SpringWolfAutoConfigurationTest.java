package io.github.stavshamir.springwolf.integrationtests;

import com.asyncapi.v2.model.info.Info;
import io.github.stavshamir.springwolf.asyncapi.controller.AsyncApiController;
import io.github.stavshamir.springwolf.configuration.AsyncApiDocket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests loading or ignoring the SpringWolfAutoConfiguration
 */
public class SpringWolfAutoConfigurationTest {

    @SpringBootTest(classes = TestApplication.class)
    public static class TestSpringWolfEnabled{
        @Autowired
        private AsyncApiController asyncApiController;

        @Test
        public void autoconfigurationShouldBeLoaded(){
            assertThat(asyncApiController).isNotNull();
        }

    }

    @SpringBootTest(classes = TestApplication.class)
    @TestPropertySource(properties = {"springwolf.enabled=false"})
    public static class TestSpringWolfDisabled{

        @Autowired
        private ObjectProvider<AsyncApiController> asyncApiControllerObjectProvider;


        @Test
        public void autoconfigurationShouldNotBeLoaded(){
            assertThat(asyncApiControllerObjectProvider.getIfAvailable()).isNull();
        }

    }


    @SpringBootApplication
    static class TestApplication{

        @Bean
        public AsyncApiDocket docket() {
            return AsyncApiDocket.builder()
                    .info(Info.builder()
                            .title("AsyncApiDocketConfiguration-title")
                            .version("AsyncApiDocketConfiguration-version")
                            .build())
                    .build();
        }

    }

}
