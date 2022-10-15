export const CHANNEL_ANCHOR_PREFIX = "channel-"
export interface Channel {
    name: string;
    anchorIdentifier: string;
    description?: string;
    operation: Operation;
}

export type OperationType = "publish" | "subscribe";
export interface Operation {
    message: Message;
    bindings?: { [type: string]: any };
    protocol: string;
    operation: OperationType;
}

export interface Message {
    name: string;
    title: string;
    description?: string;
    payload: { $ref: string };
    headers: { $ref: string };
}
