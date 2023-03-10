import {Schema} from './schema.model';

export const CHANNEL_ANCHOR_PREFIX = '#channel-';
export interface Channel {
    name: string;
    anchorIdentifier: string;
    description?: string;
    operation: Operation;
}

export type OperationType = 'publish' | 'subscribe';
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
      anchorUrl: string;
    };
    headers: {
      name: string
      anchorUrl: string;
    };
    bindings?: Map<string, MessageBinding>;
    rawBindings?: {[protocol: string]: object};
}

export interface MessageBinding {
  [protocol: string]: string | Schema;
}
