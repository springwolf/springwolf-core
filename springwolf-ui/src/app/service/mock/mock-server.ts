/* SPDX-License-Identifier: Apache-2.0 */
import {
  InMemoryDbService,
  RequestInfo,
  STATUS,
} from "angular-in-memory-web-api";
import { exampleSchemas } from "./example-data";

export class MockServer implements InMemoryDbService {
  private mockData = this.selectMockData();

  createDb() {
    return {};
  }

  get(reqInfo: RequestInfo) {
    if (reqInfo.req.url.endsWith("/docs")) {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: this.mockData.value,
        };
      });
    } else if (reqInfo.req.url.indexOf("/docs/") !== -1) {
      const group = reqInfo.req.url.split("/docs/")[1] as string;
      return reqInfo.utils.createResponse$(() => {
        if (
          this.mockData.groups === undefined ||
          this.mockData.groups[group] === undefined
        ) {
          return { status: STATUS.NOT_FOUND, body: undefined };
        }
        return {
          status: STATUS.OK,
          body: this.mockData.groups[group],
        };
      });
    } else if (reqInfo.req.url.endsWith("/publish")) {
      return reqInfo.utils.createResponse$(() => {
        return { status: STATUS.OK, body: {} };
      });
    } else if (reqInfo.req.url.endsWith("/ui-config")) {
      if (!this.mockData.uiConfig) {
        return { status: STATUS.NOT_FOUND, body: undefined };
      }
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: this.mockData.uiConfig,
        };
      });
    }

    return reqInfo.utils.getPassThruBackend().handle(reqInfo.req);
  }

  post(reqInfo: RequestInfo) {
    if (reqInfo.req.url.endsWith("/publish")) {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
        };
      });
    }

    return reqInfo.utils.getPassThruBackend().handle(reqInfo.req);
  }

  private selectMockData() {
    const hostname = window.location.hostname;

    const matchedExample = Object.keys(exampleSchemas).filter((el) =>
      hostname.includes(el)
    );
    if (0 < matchedExample.length) {
      return exampleSchemas[matchedExample[0]];
    }

    // Kafka is default
    return exampleSchemas["kafka"];
  }
}
