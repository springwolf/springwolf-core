/* SPDX-License-Identifier: Apache-2.0 */
export type ServerAsyncApiSchemaOrRef = ServerAsyncApiSchema | { $ref: string };

export interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format?: string;
  enum?: string[];

  properties?: {
    [key: string]: ServerAsyncApiSchemaOrRef;
  };
  allOf?: ServerAsyncApiSchemaOrRef[];
  anyOf?: ServerAsyncApiSchemaOrRef[];
  oneOf?: ServerAsyncApiSchemaOrRef[];

  items?: ServerAsyncApiSchemaOrRef;
  examples?: any[];

  required?: string[];
  minimum?: number;
  maximum?: number;
  exclusiveMinimum?: number;
  exclusiveMaximum?: number;
}
