/* SPDX-License-Identifier: Apache-2.0 */
import {
  InMemoryDbService,
  RequestInfo,
  STATUS,
} from "angular-in-memory-web-api";
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
