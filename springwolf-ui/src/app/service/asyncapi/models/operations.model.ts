/* SPDX-License-Identifier: Apache-2.0 */
import { ServerBindings } from "./bindings.model";

export interface ServerOperations {
  [key: string]: ServerOperation;
}

export interface ServerOperation {
  action: string;
  description?: string;
  channel: {
    $ref: string;
  };
  messages: {
    $ref: string;
  }[];
  bindings?: ServerBindings;
}

export interface ServerOperationMessage {
  $ref: string;
}
