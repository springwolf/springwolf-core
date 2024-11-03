/* SPDX-License-Identifier: Apache-2.0 */
import mockSpringwolfAmqp from "../../../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json";
import mockSpringwolfCloudStream from "../../../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json";
import mockSpringwolfKafka from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json";
import mockSpringwolfKafkaGroupVehicles from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/groups/vehicles.json";
import mockSpringwolfKafkaUiConfig from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/ui-config.json";
import mockSpringwolfJms from "../../../../../springwolf-examples/springwolf-jms-example/src/test/resources/asyncapi.json";
import mockSpringwolfSns from "../../../../../springwolf-examples/springwolf-sns-example/src/test/resources/asyncapi.json";
import mockSpringwolfSqs from "../../../../../springwolf-examples/springwolf-sqs-example/src/test/resources/asyncapi.json";
import mockSpringwolfStomp from "../../../../../springwolf-examples/springwolf-stomp-example/src/test/resources/asyncapi.json";
import { ServerAsyncApi } from "../asyncapi/models/asyncapi.model";
import { ServerUiConfig } from "../asyncapi/models/ui.model";

export const exampleSchemas: {
  [key: string]: {
    value: ServerAsyncApi;
    groups?: { [key: string]: ServerAsyncApi };
    uiConfig?: ServerUiConfig;
  };
} = {
  amqp: { value: mockSpringwolfAmqp },
  "cloud-stream": { value: mockSpringwolfCloudStream },
  jms: { value: mockSpringwolfJms },
  kafka: {
    value: mockSpringwolfKafka,
    groups: { "Only Vehicles": mockSpringwolfKafkaGroupVehicles },
    uiConfig: mockSpringwolfKafkaUiConfig,
  },
  sns: { value: mockSpringwolfSns },
  sqs: { value: mockSpringwolfSqs },
  stomp: { value: mockSpringwolfStomp },
};
