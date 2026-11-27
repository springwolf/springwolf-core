// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp.integrationtests.application.basic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    @RabbitListener(queues = "example-topic", autoStartup = "false")
    public void receive(String payload) {}
}
