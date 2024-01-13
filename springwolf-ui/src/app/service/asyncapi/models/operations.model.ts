/* SPDX-License-Identifier: Apache-2.0 */
import { ServerBindings } from "./bindings.model";

export interface ServerOperations {
  [key: string]: ServerOperation;
}

export type ServerOperationAction = "receive" | "send";

export interface ServerOperation {
  action: ServerOperationAction;
  channel: {
    $ref: string;
  };
  messages: ServerOperationMessage[];
  bindings: ServerBindings;
}

export interface ServerOperationMessage {
  $ref: string;
}
