import {ServerAsyncApiChannelMessage} from "./asyncapi.model";

export interface ServerChannels {
  [key: string]: {
    description?: string;
    subscribe?: {
      message: ServerAsyncApiChannelMessage;
      bindings?: { [protocol: string]: object }; // TODO ServerBindings?
    };
    publish?: {
      message: ServerAsyncApiChannelMessage;
      bindings?: { [protocol: string]: object };
    };
  };
}
