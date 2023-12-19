import {ServerAsyncApiMessage} from "./message.model";
import {ServerAsyncApiInfo} from "./info.models";
import { ServerServers} from "./servers.model";
import { ServerChannels} from "./channels.model";
import { ServerComponents} from "./components.model";
import {ServerOperations} from "./operations.model";

export type ServerAsyncApiChannelMessage =
  | ServerAsyncApiMessage
  | { oneOf: ServerAsyncApiMessage[] };

export interface ServerAsyncApi {
  asyncapi: string;
  info: ServerAsyncApiInfo;
  servers: ServerServers;
  channels: ServerChannels;
  operations: ServerOperations;
  components: ServerComponents ;
}

