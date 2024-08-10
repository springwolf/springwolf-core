/* SPDX-License-Identifier: Apache-2.0 */
import { Bindings } from "./bindings.model";
import { Operation } from "./operation.model";

export const CHANNEL_ANCHOR_PREFIX = "channel-";
export interface ChannelOperation {
  name: string;
  anchorIdentifier: string;
  anchorUrl: string;
  description?: string;
  operation: Operation;
  bindings: Bindings;
}

export interface Channel {
  name: string;
  anchorIdentifier: string;
  description?: string;
  operations: ChannelOperation[];
  bindings: Bindings;
}
