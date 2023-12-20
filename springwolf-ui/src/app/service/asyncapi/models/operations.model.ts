/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiMessage } from "./message.model";

export interface ServerOperations {
  action: "receive" | "send";
  channel: {
    $ref: string;
  };
  messages: ServerAsyncApiMessage[];
}
