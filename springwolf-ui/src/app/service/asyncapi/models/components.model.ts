import {ServerAsyncApiSchema} from "./schema.model";
import {ServerAsyncApiMessage} from "./message.model";

export interface ServerComponents {
  schemas: Map<string, ServerAsyncApiSchema>;
}
