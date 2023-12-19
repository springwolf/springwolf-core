import {ServerAsyncApiSchema} from "./schema.model";

export interface ServerComponents {
  schemas: Map<string, ServerAsyncApiSchema>;
}
