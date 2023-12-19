/* SPDX-License-Identifier: Apache-2.0 */
import { Info } from "./info.model";
import { Server } from "./server.model";
import { ChannelOperation } from "./channel.model";
import { Schema } from "./schema.model";

export interface AsyncApi {
  info: Info;
  servers: Map<string, Server>;
  channelOperations: ChannelOperation[];
  components: { schemas: Map<string, Schema> };
}
