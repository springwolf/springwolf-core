import {ServerAsyncApiSchema} from "./schema.model";

export interface ServerBindings {
  [protocol: string]: ServerBinding;
}

export interface ServerBinding {
  [bindingProperty: string]: string | ServerBinding;
}