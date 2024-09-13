// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.amqp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AmqpConstants {

    // Exchanges
    public static final String EXCHANGE_EXAMPLE_TOPIC_EXCHANGE = "example-topic-exchange";
    public static final String EXCHANGE_CRUD_TOPIC_EXCHANGE_1 = "CRUD-topic-exchange-1";
    public static final String EXCHANGE_CRUD_TOPIC_EXCHANGE_2 = "CRUD-topic-exchange-2";
    /**
     *  Direct Exchange - Routing is based on 'Routing key'.
     *  Messages are 'routed' towards queue which name is equals to 'routing key' <BR/>
     *  Note:<BR/>
     *  The 'Default exchange' is a 'Direct exchange' with an empty name ( name= "")
     */
    public static final String EXCHANGE_DEFAULT_EXCHANGE = "";

    // Routing keys
    /**
     * When a queue is bound with "#" (hash) binding key,
     * it will receive all the messages, regardless of the routing key - like in fanout exchange.
     */
    public static final String ROUTING_KEY_ALL_MESSAGES = "#";

    public static final String ROUTING_KEY_EXAMPLE_TOPIC_ROUTING_KEY = "example-topic-routing-key";

    // Queues
    public static final String QUEUE_EXAMPLE_QUEUE = "example-queue";
    public static final String QUEUE_ANOTHER_QUEUE = "another-queue";
    public static final String QUEUE_MULTI_PAYLOAD_QUEUE = "multi-payload-queue";
    public static final String QUEUE_EXAMPLE_BINDINGS_QUEUE = "example-bindings-queue";

    public static final String QUEUE_CREATE = "queue-create";
    public static final String QUEUE_READ = "queue-read";
    public static final String QUEUE_DELETE = "queue-delete";
    public static final String QUEUE_UPDATE = "queue-update";
}
