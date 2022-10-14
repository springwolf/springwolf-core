export interface Channel {
    name: string;
    description?: string;
    operation: Operation;
}

export interface Operation {
    type: string;
    message: Message;
    bindings?: { [type: string]: any };
    protocol: string;
    operation: string;
}

export interface Message {
    name: string;
    title: string;
    description?: string;
    payload: { $ref: string };
    headers: { $ref: string };
}
