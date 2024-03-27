/* SPDX-License-Identifier: Apache-2.0 */
import mockSpringwolfAmqp from "../../../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json";
import mockSpringwolfCloudStream from "../../../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json";
import mockSpringwolfKafka from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json";
import mockSpringwolfSns from "../../../../../springwolf-examples/springwolf-sns-example/src/test/resources/asyncapi.json";
import mockSpringwolfSqs from "../../../../../springwolf-examples/springwolf-sqs-example/src/test/resources/asyncapi.json";
import mockSpringwolfJms from "../../../../../springwolf-examples/springwolf-jms-example/src/test/resources/asyncapi.json";
import { ServerAsyncApi } from "../asyncapi/models/asyncapi.model";

export const exampleSchemas: {
  plugin: string;
  value: ServerAsyncApi;
}[] = [
  { plugin: "amqp", value: mockSpringwolfAmqp },
  { plugin: "cloud-stream", value: mockSpringwolfCloudStream },
  { plugin: "jms", value: mockSpringwolfJms },
  { plugin: "kafka", value: mockSpringwolfKafka },
  { plugin: "sns", value: mockSpringwolfSns },
  { plugin: "sqs", value: mockSpringwolfSqs },
];
