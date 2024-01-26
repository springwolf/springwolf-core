/* SPDX-License-Identifier: Apache-2.0 */
import { ServerBindings } from "./bindings.model";

export interface ServerAsyncApiMessage {
  name: string;
  title: string;
  description?: string;
  payload: {
    schemaFormat: string;
    schema: {
      $ref: string;
    };
  };
  headers: { $ref: string };
  bindings?: ServerBindings;
}
