/* SPDX-License-Identifier: Apache-2.0 */
import mockSpringwolfAmqp from "../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json";
import mockSpringwolfCloudStream from "../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json";
import mockSpringwolfKafka from "../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json";
import mockSpringwolfSns from "../../../springwolf-examples/springwolf-sns-example/src/test/resources/asyncapi.json";
import mockSpringwolfSqs from "../../../springwolf-examples/springwolf-sqs-example/src/test/resources/asyncapi.json";
import mockSpringwolfJms from "../../../springwolf-examples/springwolf-jms-example/src/test/resources/asyncapi.json";

type ExampleProject = "amqp" | "cloud-stream" | "kafka" | "sns" | "sqs" | "jms";

export function getExampleProject(): ExampleProject {
  return process.env.SPRINGWOLF_EXAMPLE || "amqp";
}

export function getExampleAsyncApi() {
  switch (getExampleProject()) {
    case "amqp":
      return mockSpringwolfAmqp;
    case "cloud-stream":
      return mockSpringwolfCloudStream;
    case "jms":
      return mockSpringwolfJms;
    case "kafka":
      return mockSpringwolfKafka;
    case "sns":
      return mockSpringwolfSns;
    case "sqs":
      return mockSpringwolfSqs;
  }
}
