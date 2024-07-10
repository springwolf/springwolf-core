/* SPDX-License-Identifier: Apache-2.0 */
import { Message } from "./message.model";
import { Bindings } from "./bindings.model";

export type OperationType = "receive" | "send";
export interface Operation {
  description?: string;
  message: Message;
  bindings: Bindings;
  protocol: string;
  servers: OperationServer[];
  operationType: OperationType;
}

export interface OperationServer {
  name: string;
  anchorIdentifier: string;
  anchorUrl: string;
}
