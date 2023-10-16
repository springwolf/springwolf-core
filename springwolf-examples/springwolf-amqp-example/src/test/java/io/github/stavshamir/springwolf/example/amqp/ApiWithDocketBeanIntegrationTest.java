// SPDX-License-Identifier: Apache-2.0
package io.github.stavshamir.springwolf.example.amqp;

import org.springframework.test.context.TestPropertySource;

/**
 * Api integrationtest based on a SpringBoot application that defines a custom Docket bean. This contains Info and
 * Server Informations as well as some explicit Producer and Consumer definitions.
 */
@TestPropertySource(properties = {"customAsyncApiDocketBean=true"})
public class ApiWithDocketBeanIntegrationTest extends BaseApiIntegrationTest {

    @Override
    protected String getExpectedApiFileName() {
        return "/asyncapi.json";
    }
}
