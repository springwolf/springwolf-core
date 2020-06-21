export interface Channel {
    description?: string;
    operation: Operation;
}

export interface Operation {
    type: "SUBSCRIBE" | "PUBLISH";
    message: Message;
    bindings?: any;
}

export interface Message {
    name: string;
    title: string;
    payload: { $ref: string};
}