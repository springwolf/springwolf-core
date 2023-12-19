/* SPDX-License-Identifier: Apache-2.0 */
import { Binding, Bindings } from "./bindings.model";

export const CHANNEL_ANCHOR_PREFIX = "#channel-";
export interface ChannelOperation {
  name: string;
  anchorIdentifier: string;
  description?: string;
  operation: Operation;
}

export type OperationType = "receive" | "send";
export interface Operation {
  message: Message;
  bindings?: Bindings;
  protocol: string;
  operationType: OperationType;
}

export interface Message {
  name: string;
  title: string;
  description?: string;
  payload: {
    name: string;
    title: string;
    anchorUrl: string;
  };
  headers: {
    name: string;
    title: string;
    anchorUrl: string;
  };
  bindings?: Map<string, Binding>;
  rawBindings?: { [protocol: string]: object };
}
