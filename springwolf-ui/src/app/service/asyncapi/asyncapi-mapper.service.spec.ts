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

  exampleSchemas.forEach((testData) => {
    it(
      "should be able to parse example AsyncApi.json without errors - " +
        testData.plugin +
        " example",
      () => {
        service.toAsyncApi(testData.value);

        expect(notificationService.showError).not.toHaveBeenCalled();
        expect(notificationService.showWarning).not.toHaveBeenCalled();
      }
    );
  });

  exampleSchemas.forEach((testData) => {
    const parser = new Parser();
    it(
      "should be a valid AsyncApi schema - " + testData.plugin + " example",
      async () => {
        const diagnostics = await parser.validate(
          JSON.stringify(testData.value)
        );

        // In case you are debugging, copy the asyncapi.json to AsyncApi Studio as it displays better error messages.
        // Remove workaround after https://github.com/asyncapi/spec/issues/1038 is clarified
        expect(
          diagnostics
            .map((el) => el.message)
            .filter(
              (message) => message === '"0" property type must be object"'
            )
        ).toHaveLength(0);
        expect(
          diagnostics.filter(
            (el) => el.message === '"0" property type must be object"'
          )
        ).toHaveLength(0);
      }
    );
  });
});
