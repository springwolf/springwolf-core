// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.configuration;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class RabbitMqReadinessVerifier {

    private static final int RABBITMQ_MAX_CONNECT_TIMEOUT_MS = 60000;
    private static final int RABBITMQ_INTERVAL_WAIT_MS = 5000;
    private final ConnectionFactory connectionFactory;

    public RabbitMqReadinessVerifier(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;

        verifyRabbitMqIsUp();
    }

    private void verifyRabbitMqIsUp() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < RABBITMQ_MAX_CONNECT_TIMEOUT_MS) {
            try {
                RabbitAdmin admin = new RabbitAdmin(connectionFactory);
                admin.afterPropertiesSet();

                RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
                rabbitTemplate.execute(channel -> null); // dummy operation throws exception when amqp is not up

                break;
            } catch (AmqpConnectException e) {
                try {
                    Thread.sleep(RABBITMQ_INTERVAL_WAIT_MS);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("RabbitMQ is not running", ie);
                }
            }
        }
    }
}
