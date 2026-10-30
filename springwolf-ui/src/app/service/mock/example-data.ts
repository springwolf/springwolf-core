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
import coreAsyncApiFqn from "../../../../../springwolf-core/src/test/resources/application/asyncapi.fqn.json";
import coreAsyncApiListener from "../../../../../springwolf-core/src/test/resources/application/asyncapi.listener.json";
import coreAsyncApiOneOf from "../../../../../springwolf-core/src/test/resources/application/asyncapi.oneof.json";
import coreAsyncApiOpenApiV3 from "../../../../../springwolf-core/src/test/resources/application/asyncapi.openapiv3.json";
import coreAsyncApiOpenApiV31 from "../../../../../springwolf-core/src/test/resources/application/asyncapi.openapiv31.json";
import coreAsyncApiPolymorphic from "../../../../../springwolf-core/src/test/resources/application/asyncapi.polymorphic.json";
import coreAsyncApiPublisher from "../../../../../springwolf-core/src/test/resources/application/asyncapi.publisher.json";
import coreAsyncApiRef from "../../../../../springwolf-core/src/test/resources/application/asyncapi.ref.json";
import { ServerAsyncApi } from "../asyncapi/models/asyncapi.model";
import { ServerUiConfig } from "../asyncapi/models/ui.model";

export const coreIntegrationSchemas = {
  fqn: coreAsyncApiFqn,
  listener: coreAsyncApiListener,
  oneOf: coreAsyncApiOneOf,
  openApiV3: coreAsyncApiOpenApiV3,
  openApiV31: coreAsyncApiOpenApiV31,
  polymorphic: coreAsyncApiPolymorphic,
  publisher: coreAsyncApiPublisher,
  ref: coreAsyncApiRef,
};

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
