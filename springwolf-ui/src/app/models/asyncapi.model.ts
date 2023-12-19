/* SPDX-License-Identifier: Apache-2.0 */
import { Info } from "./info.model";
import { Server } from "./server.model";
import { Channel } from "./channel.model";
import { Schema } from "./schema.model";

export interface AsyncApi {
  info: Info;
  servers: Map<string, Server>;
  channels: Channel[]; // TODO: Channel + Operation + Message
  components: { schemas: Map<string, Schema> };
}
