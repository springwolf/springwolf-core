// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp.configuration;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class RabbitLifecycleConfiguration implements SmartLifecycle {

    private final ConnectionFactory connectionFactory;
    private boolean isRunning = false;

    public RabbitLifecycleConfiguration(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        start();
    }

    @Override
    public void start() {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 60000) { // 60000 milliseconds = 1 minute
            try {
                RabbitAdmin admin = new RabbitAdmin(connectionFactory);
                admin.afterPropertiesSet(); // this will block until the connection is ready

                // Create a RabbitTemplate and execute a dummy operation to ensure the connection is open and ready
                RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
                rabbitTemplate.execute(channel -> {
                    // This is a dummy operation
                    return null;
                });

                isRunning = true;
                break; // exit the loop if the connection is successfully opened
            } catch (AmqpConnectException e) {
                try {
                    Thread.sleep(5000); // 10000 milliseconds = 10 seconds
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("Thread was interrupted", ie);
                }
            }
        }

        if (!isRunning) {
            throw new IllegalStateException("Unable to connect to amqp (Did you start all docker containers?)");
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
