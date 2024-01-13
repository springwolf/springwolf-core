/* SPDX-License-Identifier: Apache-2.0 */
import { Operation } from "./operation.model";

export const CHANNEL_ANCHOR_PREFIX = "#channel-";
export interface ChannelOperation {
  name: string;
  anchorIdentifier: string;
  description?: string;
  operation: Operation;
}
