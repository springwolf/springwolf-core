/* SPDX-License-Identifier: Apache-2.0 */
import { Schema } from "./schema.model";

export const CHANNEL_ANCHOR_PREFIX = "#channel-";
export interface ChannelOperation {
  name: string;
  anchorIdentifier: string;
  description?: string;
  operation: Operation;
}

export type OperationType = "publish" | "subscribe"; // TODO: rename
export interface Operation {
  message: Message;
  bindings?: { [protocol: string]: any };
  protocol: string;
  operation: OperationType;
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

export interface Binding {
  [protocol: string]: string | Binding;
}
