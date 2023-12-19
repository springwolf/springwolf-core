import {ServerAsyncApiSchema} from "./schema.model";

export interface ServerBindings {
  [protocol: string]: ServerAsyncApiSchema | string; // TODO: review
}
