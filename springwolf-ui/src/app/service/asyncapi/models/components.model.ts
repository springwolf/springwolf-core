/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiMessage } from "./message.model";
import { ServerAsyncApiSchema } from "./schema.model";

export interface ServerComponents {
  schemas: {
    [key: string]: ServerAsyncApiSchema;
  };
  messages: {
    [key: string]: ServerAsyncApiMessage;
  };
}
