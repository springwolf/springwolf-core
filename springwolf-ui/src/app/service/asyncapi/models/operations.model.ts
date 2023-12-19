export interface ServerOperations {
  action: "receive" | "send";
  channel: {
    "$ref": string;
  };
  messages: { "$ref": string}[];
}
