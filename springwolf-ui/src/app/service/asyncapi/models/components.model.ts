import {ServerAsyncApiSchema} from "./schema.model";
import {ServerAsyncApiMessage} from "./message.model";

export interface ServerComponents {
  messages: Map<string, ServerAsyncApiMessage>;
  schemas: Map<string, ServerAsyncApiSchema>;
}
