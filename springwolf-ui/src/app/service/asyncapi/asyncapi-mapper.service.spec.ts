/* SPDX-License-Identifier: Apache-2.0 */
import { INotificationService } from "../notification.service";
import { AsyncApiMapperService } from "./asyncapi-mapper.service";

import * as mockSpringwolfAmqp from "../../../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json";
import * as mockSpringwolfCloudStream from "../../../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json";
import * as mockSpringwolfKafka from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json";
import * as mockSpringwolfSns from "../../../../../springwolf-examples/springwolf-sns-example/src/test/resources/asyncapi.json";
import * as mockSpringwolfSqs from "../../../../../springwolf-examples/springwolf-sqs-example/src/test/resources/asyncapi.json";
import * as mockSpringwolfJms from "../../../../../springwolf-examples/springwolf-jms-example/src/test/resources/asyncapi.json";

describe("AsyncApiMapperService", () => {
  let notificationService: INotificationService;
  let service: AsyncApiMapperService;

  beforeEach(() => {
    notificationService = {
      showError: jest.fn(),
      showWarning: jest.fn(),
    };

    service = new AsyncApiMapperService(notificationService);
  });

  [
    { plugin: "amqp", value: mockSpringwolfAmqp },
    { plugin: "cloudStream", value: mockSpringwolfCloudStream },
    { plugin: "kafka", value: mockSpringwolfKafka },
    { plugin: "sns", value: mockSpringwolfSns },
    { plugin: "sqs", value: mockSpringwolfSqs },
    { plugin: "jms", value: mockSpringwolfJms },
  ].forEach((testData) => {
    it(
      "should be able to parse example AsyncApi.json without errors - " +
        testData.plugin,
      () => {
        service.toAsyncApi(testData.value);

        expect(notificationService.showError).not.toHaveBeenCalled();
        expect(notificationService.showWarning).not.toHaveBeenCalled();
      }
    );
  });
});
