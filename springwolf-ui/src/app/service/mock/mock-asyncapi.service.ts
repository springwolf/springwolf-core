/* SPDX-License-Identifier: Apache-2.0 */
import { of } from "rxjs";
import { AsyncApiMapperService } from "../asyncapi/asyncapi-mapper.service";
import { exampleSchemas } from "./example-data";

const asyncApiMapperService = new AsyncApiMapperService({
  showError: jest.fn(),
  showWarning: jest.fn(),
});
export const mockedExampleSchemaMapped = asyncApiMapperService.toAsyncApi(
  exampleSchemas["kafka"].value
)!!;
export const mockedAsyncApiService: { getAsyncApi: jest.Mock } = {
  getAsyncApi: jest.fn().mockReturnValue(of(mockedExampleSchemaMapped)),
};
