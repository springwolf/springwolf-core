export interface Channel {
    description?: string;
    operation: Operation;
}

export interface Operation {
    type: string;
    message: Message;
    bindings?: { [type: string]: any };
}

export interface Message {
    name: string;
    title: string;
    payload: { $ref: string };
}
