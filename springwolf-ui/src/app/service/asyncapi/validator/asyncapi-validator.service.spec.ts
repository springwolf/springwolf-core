/* SPDX-License-Identifier: Apache-2.0 */
import Ajv from "ajv";
import { createGenerator } from "ts-json-schema-generator";
import path from "node:path";
import expectedSchema from "./server-async-api.schema.json";
import { exampleSchemas } from "../../mock/example-data";
import * as fs from "node:fs";
import { INotificationService } from "../../notification.service";
import { AsyncApiValidatorService } from "./asyncapi-validator.service";
import { ServerAsyncApi } from "../models/asyncapi.model";

const config = {
  path: path.join(__dirname, "../models/asyncapi.model.ts"),
  tsconfig: path.join(__dirname, "../../../../../tsconfig.json"),
  type: "ServerAsyncApi",
};
const serverJsonSchema = createGenerator(config).createSchema("ServerAsyncApi");

describe("AsyncApiValidatorService", () => {
  const ajv = new Ajv({
    allErrors: true,
    removeAdditional: true,
  });

  let notificationService: INotificationService;
  let service: AsyncApiValidatorService;

  beforeEach(() => {
    notificationService = {
      showError: jest.fn(),
      showWarning: jest.fn(),
    };

    service = new AsyncApiValidatorService(notificationService);
    service._logToConsole = false;
  });

  for (const [plugin, pluginSchema] of Object.entries(exampleSchemas)) {
    const pluginSchemaGroups = {
      ...pluginSchema.groups,
      default: pluginSchema.value,
    };

    for (const [group, schema] of Object.entries(pluginSchemaGroups)) {
      it(
        "should verify AsyncApi schema is valid - " +
          plugin +
          " example and group " +
          group,
        () => {
          // when
          service.validate(schema);

          // then
          expect(notificationService.showError).not.toHaveBeenCalled();
          expect(notificationService.showWarning).not.toHaveBeenCalled();
        }
      );
    }
  }

  it("should verify notification service is triggered if a schema is invalid", () => {
    // given
    const invalidSchema = {
      ...exampleSchemas["kafka"].value,
      info: "invalid-value",
    } as unknown as ServerAsyncApi;

    // when
    service.validate(invalidSchema);

    // then
    expect(notificationService.showError).toHaveBeenCalled();
    expect(notificationService.showWarning).not.toHaveBeenCalled();
  });

  it("should compile to the checked-in schema", () => {
    // when
    const validate = ajv.compile(serverJsonSchema);

    // then
    fs.writeFile(
      path.join(__dirname, "server-async-api.schema.actual.json"),
      JSON.stringify(validate.schema, null, 2),
      (err) => {
        if (err) throw err;
      }
    );

    expect(validate.schema).toStrictEqual(expectedSchema);
  });
});
