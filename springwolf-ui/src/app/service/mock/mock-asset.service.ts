/* SPDX-License-Identifier: Apache-2.0 */
import { IAssetService } from "../asset.service";

export const mockedAssetService: IAssetService = {
  load: jest.fn(),
};
