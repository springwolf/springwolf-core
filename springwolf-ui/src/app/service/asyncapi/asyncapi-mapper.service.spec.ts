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

  const parser = new Parser();
  for (const [plugin, pluginSchema] of Object.entries(exampleSchemas)) {
    const pluginSchemaGroups = {
      ...pluginSchema.groups,
      default: pluginSchema.value,
    };

    for (const [group, schema] of Object.entries(pluginSchemaGroups)) {
      it(
        "should be able to parse example AsyncApi.json without errors - " +
          plugin +
          " example and group " +
          group,
        () => {
          service.toAsyncApi(schema);

          expect(notificationService.showError).not.toHaveBeenCalled();
          expect(notificationService.showWarning).not.toHaveBeenCalled();
        }
      );

      it(
        "should be a valid AsyncApi schema - " +
          plugin +
          " example and group " +
          group,
        async () => {
          const diagnostics = await parser.validate(JSON.stringify(schema));

          // In case you are debugging, copy the asyncapi.json to AsyncApi Studio as it displays better error messages.
          expect(diagnostics.map((el) => el.message)).toHaveLength(0);
          expect(diagnostics).toHaveLength(0);
        }
      );
    }
  }
});
