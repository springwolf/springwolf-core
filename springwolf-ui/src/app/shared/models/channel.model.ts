export interface Channel {
    description?: String;
    operation: Operation;
}

export interface Operation {
    type: "SUBSCRIBE" | "PUBLISH";
    message: Message;
    bindings?: any;
}

export interface Message {
    name: String;
    title: String;
    payloadReference: String;
}