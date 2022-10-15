export interface Channel {
    name: string;
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
