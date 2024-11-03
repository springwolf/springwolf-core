/* SPDX-License-Identifier: Apache-2.0 */
import { Parser } from "@asyncapi/parser";
import { exampleSchemas } from "../mock/example-data";
import { INotificationService } from "../notification.service";
import { AsyncApiMapperService } from "./asyncapi-mapper.service";

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

  for (const [plugin, testData] of Object.entries(exampleSchemas)) {
    it(
      "should be able to parse example AsyncApi.json without errors - " +
        plugin +
        " example",
      () => {
        service.toAsyncApi(testData.value);

        expect(notificationService.showError).not.toHaveBeenCalled();
        expect(notificationService.showWarning).not.toHaveBeenCalled();
      }
    );
  }

  for (const [plugin, testData] of Object.entries(exampleSchemas)) {
    const parser = new Parser();
    it(
      "should be a valid AsyncApi schema - " + plugin + " example",
      async () => {
        const diagnostics = await parser.validate(
          JSON.stringify(testData.value)
        );

        // In case you are debugging, copy the asyncapi.json to AsyncApi Studio as it displays better error messages.
        expect(diagnostics.map((el) => el.message)).toHaveLength(0);
        expect(diagnostics).toHaveLength(0);
      }
    );
  }
});
