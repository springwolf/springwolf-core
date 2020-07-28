export interface Channel {
    description?: string;
    operation: Operation;
}

export interface Operation {
    type: "SUBSCRIBE" | "PUBLISH";
    message: Message;
    bindings?: { [type: string]: any };
}

export interface Message {
    name: string;
    title: string;
    payload: { $ref: string };
}