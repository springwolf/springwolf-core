/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiMessage } from "./message.model";
import { ServerBindings } from "./bindings.model";

export interface ServerChannels {
  [key: string]: ServerChannel;
}

export interface ServerChannel {
  address: string;
  description?: string;
  messages: Map<string, ServerAsyncApiMessage>;
  bindings: ServerBindings;
}
