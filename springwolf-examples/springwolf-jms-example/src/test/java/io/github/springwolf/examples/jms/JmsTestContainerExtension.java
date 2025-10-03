// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.jms;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

/**
 * JUnit5 extension to start the localstack testcontainers once
 * and keep it running until all test classes have been completed.
 */
public class JmsTestContainerExtension implements BeforeAllCallback, AutoCloseable {

    private static volatile boolean started = false;

    static GenericContainer<?> activeMq = new GenericContainer<>(
                    DockerImageName.parse("apache/activemq-artemis:2.37.0-alpine"))
            .withExposedPorts(61616);

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!started) {
            started = true;

            beforeAllOnce();

            // Ensure closeableResource {@see #close()} method is called
            extensionContext.getRoot().getStore(GLOBAL).put("any unique name", this);
        }
    }

    private static void beforeAllOnce() {
        activeMq.start();
    }

    @Override
    public void close() {
        activeMq.stop();
    }
}
