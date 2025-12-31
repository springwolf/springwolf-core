/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiInfo } from "./info.models";
import { ServerServers } from "./servers.model";
import { ServerChannels } from "./channels.model";
import { ServerComponents } from "./components.model";
import { ServerOperations } from "./operations.model";

export interface ServerAsyncApi {
  asyncapi: string;
  info: ServerAsyncApiInfo;
  defaultContentType: string;
  servers: ServerServers | undefined;
  channels: ServerChannels;
  operations: ServerOperations;
  components: ServerComponents;
}
