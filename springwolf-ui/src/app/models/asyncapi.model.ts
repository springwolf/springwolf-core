/* SPDX-License-Identifier: Apache-2.0 */
import { Info } from "./info.model";
import { Server } from "./server.model";
import { Channel, ChannelOperation } from "./channel.model";
import { Schema } from "./schema.model";

export interface AsyncApi {
  info: Info;
  servers: Map<string, Server>;
  channels: Channel[];
  /**
   * @deprecated Start using channels instead
   */
  channelOperations: ChannelOperation[];
  components: { schemas: Map<string, Schema> };
}
