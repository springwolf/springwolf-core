/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiMessage } from "./message.model";
import { ServerAsyncApiSchema } from "./schema.model";

export type ServerMessages = Map<string, ServerAsyncApiMessage>;

export interface ServerComponents {
  schemas: Map<string, ServerAsyncApiSchema>;
  messages: ServerMessages;
}
