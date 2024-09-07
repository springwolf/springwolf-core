/* SPDX-License-Identifier: Apache-2.0 */
import { ServerBindings } from "./bindings.model";
import { ServerAsyncApiSchema } from "./schema.model";

export interface ServerAsyncApiMessage {
  name: string;
  title: string;
  description?: string;
  contentType?: string;
  payload: {
    schemaFormat: string;
    schema:
      | {
          $ref: string;
        }
      | ServerAsyncApiSchema;
  };
  headers: { $ref: string };
  bindings?: ServerBindings;
}
