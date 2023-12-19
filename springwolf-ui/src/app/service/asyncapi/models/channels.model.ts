import {ServerAsyncApiChannelMessage} from "./asyncapi.model";
import {ServerAsyncApiMessage} from "./message.model";

export interface ServerChannels {
  [key: string]: {
    address: string;
    description?: string;
    messages: Map<string, ServerAsyncApiMessage>;
  };
}
