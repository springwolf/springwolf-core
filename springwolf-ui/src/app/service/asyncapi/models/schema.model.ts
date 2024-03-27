/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format?: string;
  enum?: string[];
  properties?: {
    [key: string]: ServerAsyncApiSchema | { $ref: string };
  };
  items?: ServerAsyncApiSchema | { $ref: string };
  examples?: any[];

  required?: string[];
  minimum?: number;
  maximum?: number;
  exclusiveMinimum?: number;
  exclusiveMaximum?: number;
}
