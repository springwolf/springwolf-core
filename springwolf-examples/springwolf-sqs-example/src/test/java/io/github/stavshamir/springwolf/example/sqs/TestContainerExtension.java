package io.github.stavshamir.springwolf.example.sqs;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

public class TestContainerExtension implements BeforeAllCallback, AfterAllCallback {

    static LocalStackContainer localStack =
            new LocalStackContainer(DockerImageName.parse("localstack/localstack:2.2.0")).withServices(SQS);

    static void overrideConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.aws.endpoint", () -> localStack.getEndpoint());
        registry.add("spring.cloud.aws.credentials.access-key", localStack::getAccessKey);
        registry.add("spring.cloud.aws.credentials.secret-key", localStack::getSecretKey);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        localStack.start();

        localStack.execInContainer(
                "awslocal", "sqs", "create-queue", "--queue-name", "another-queue", "--region", "eu-central-1");
        localStack.execInContainer(
                "awslocal", "sqs", "create-queue", "--queue-name", "example-queue", "--region", "eu-central-1");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        localStack.stop();
    }
}
