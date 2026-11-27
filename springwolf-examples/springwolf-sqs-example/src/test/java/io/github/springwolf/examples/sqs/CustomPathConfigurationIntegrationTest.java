// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(
        classes = {SpringwolfSqsExampleApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({SqsTestContainerExtension.class})
@TestPropertySource(
        properties = {"springwolf.path.base=/my-custom/springwolf/endpoint/test", "springwolf.path.docs=/apispec"})
@AutoConfigureRestTestClient
class CustomPathConfigurationIntegrationTest {

    @DynamicPropertySource
    static void setUpTestContainers(DynamicPropertyRegistry registry) {
        SqsTestContainerExtension.overrideConfiguration(registry);
    }

    @Autowired
    private RestTestClient restTestClient;

    @Test
    void getSpec() {
        // given
        String url = "/my-custom/springwolf/endpoint/test/apispec";

        // when
        RestTestClient.ResponseSpec response = restTestClient.get().uri(url).exchange();

        // then
        response.expectStatus().isOk();
    }

    @Test
    void canPublish() {
        // given
        String url = "/my-custom/springwolf/endpoint/test/plugin/sqs/publish";

        // when
        RestTestClient.ResponseSpec response = restTestClient.get().uri(url).exchange();

        // then
        response.expectStatus().isOk();
    }

    @Test
    void asyncapiDocsShouldReturnTheCorrectJsonResponse() {
        // given
        String url = "/my-custom/springwolf/endpoint/test/asyncapi-ui.html";

        // when
        RestTestClient.ResponseSpec response = restTestClient.get().uri(url).exchange();

        // then
        response.expectStatus().isOk();
    }
}
