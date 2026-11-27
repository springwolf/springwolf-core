// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.plugins.amqp;

import io.github.springwolf.plugins.amqp.asyncapi.scanners.channels.RabbitQueueBeanScanner;
import io.github.springwolf.plugins.amqp.fixtures.SpringwolfIntegrationTest;
import io.github.springwolf.plugins.amqp.publish.SpringwolfAmqpPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringwolfIntegrationTest
class SpringContextIntegrationTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private RabbitQueueBeanScanner bean;

    @Autowired
    private Optional<SpringwolfAmqpPublisher> publisher;

    @Test
    void contextWithApplicationProperties() {
        assertThat(context).isNotNull();
        assertThat(bean).isNotNull();
    }

    @Test
    void startsWithoutPublisherBean() {
        assertThat(publisher).isNotPresent();
    }
}
