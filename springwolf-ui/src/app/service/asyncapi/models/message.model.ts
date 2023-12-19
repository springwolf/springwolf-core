import {ServerBindings} from "./bindings.model";

export interface ServerAsyncApiMessage {
  name: string;
  title: string;
  description?: string;
  payload: { $ref: string };
  headers: { $ref: string };
  bindings: { [protocol: string]: ServerBindings };
}
