// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import org.springframework.test.context.TestPropertySource;

/**
 * Api integrationtest based on a SpringBoot application that defines all info and server properties via
 * spring environment (from application.properties).
 */
@TestPropertySource(properties = {"customAsyncApiDocketBean=false"})
public class ApiWithDocketFromEnvironmentIntegrationTest extends BaseApiIntegrationTest {

    @Override
    protected String getExpectedApiFileName() {
        return "/asyncapi_withdocketfromenvironment.json";
    }
}
