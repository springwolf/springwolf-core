/* SPDX-License-Identifier: Apache-2.0 */
export type ServerAsyncApiSchemaOrRef = ServerAsyncApiSchema | { $ref: string };

export interface ServerAsyncApiSchema {
  description?: string;
  deprecated?: boolean;

  enum?: string[];
  examples?: any[];

  type: string;
  format?: string;
  // type == ref
  not?: ServerAsyncApiSchemaOrRef;
  allOf?: ServerAsyncApiSchemaOrRef[];
  anyOf?: ServerAsyncApiSchemaOrRef[];
  oneOf?: ServerAsyncApiSchemaOrRef[];
  // type == object
  properties?: {
    [key: string]: ServerAsyncApiSchemaOrRef;
  };
  required?: string[];
  // type == array
  items?: ServerAsyncApiSchemaOrRef;
  minItems?: number;
  maxItems?: number;
  uniqueItems?: boolean;
  // type == string
  minLength?: number;
  maxLength?: number;
  pattern?: string;
  // type == number
  minimum?: number;
  maximum?: number;
  exclusiveMinimum?: number;
  exclusiveMaximum?: number;
  multipleOf?: number;
}
