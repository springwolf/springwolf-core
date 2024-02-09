/* SPDX-License-Identifier: Apache-2.0 */
import {
  InMemoryDbService,
  RequestInfo,
  STATUS,
} from "angular-in-memory-web-api";
import mockSpringwolfAmqp from "../../../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json";
import mockSpringwolfCloudStream from "../../../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json";
import mockSpringwolfKafka from "../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json";
import mockSpringwolfSns from "../../../../../springwolf-examples/springwolf-sns-example/src/test/resources/asyncapi.json";
import mockSpringwolfSqs from "../../../../../springwolf-examples/springwolf-sqs-example/src/test/resources/asyncapi.json";
import mockSpringwolfJms from "../../../../../springwolf-examples/springwolf-jms-example/src/test/resources/asyncapi.json";
import { exampleSchemas } from "./example-data";

export class MockServer implements InMemoryDbService {
  createDb() {
    return { kafka: [] };
  }

  get(reqInfo: RequestInfo) {
    console.log("Returning mock data");
    if (reqInfo.req.url.endsWith("/docs")) {
      const body = this.selectMockData();
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: body,
        };
      });
    }

    return undefined;
  }

  post(reqInfo: RequestInfo) {
    if (reqInfo.req.url.endsWith("/publish")) {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
        };
      });
    }

    return undefined;
  }

  private selectMockData() {
    const hostname = window.location.hostname;

    const matchedExample = exampleSchemas.filter((el) =>
      hostname.includes(el.plugin)
    );
    if (0 < matchedExample.length) {
      return matchedExample[0].value;
    }

    // Kafka is default
    return exampleSchemas.find((el) => el.plugin === "kafka").value;
  }
}
