// SPDX-License-Identifier: Apache-2.0
package io.github.springwolf.examples.sns;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;

/**
 * JUnit5 extension to start the localstack testcontainers once
 * and keep it running until all test classes have been completed.
 */
public class SnsTestContainerExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static volatile boolean started = false;

    static LocalStackContainer localStack =
            new LocalStackContainer(DockerImageName.parse("localstack/localstack:2.2.0")).withServices(SNS);

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!started) {
            started = true;

            beforeAllOnce();

            // Ensure closeableResource {@see #close()} method is called
            extensionContext.getRoot().getStore(GLOBAL).put("any unique name", this);
        }
    }

    private static void beforeAllOnce() throws IOException, InterruptedException {
        localStack.start();

        localStack.execInContainer(
                "awslocal", "sns", "create-topic", "--name", "another-topic", "--region", "eu-central-1");
        localStack.execInContainer(
                "awslocal", "sns", "create-topic", "--name", "example-topic", "--region", "eu-central-1");
    }

    @Override
    public void close() throws Throwable {
        localStack.stop();
    }

    static void overrideConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.endpoint", () -> localStack.getEndpoint());
        registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    }
}
