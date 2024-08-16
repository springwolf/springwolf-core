/* SPDX-License-Identifier: Apache-2.0 */
import { of } from "rxjs";
import { AsyncApiMapperService } from "../asyncapi/asyncapi-mapper.service";
import { exampleSchemas } from "./example-data";
import { IAssetService } from "../asset.service";

export const mockedAssetService: IAssetService = {
  load: jest.fn(),
};
