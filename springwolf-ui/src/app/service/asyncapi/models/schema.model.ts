/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format: string;
  enum: string[];
  properties?: Map<string, ServerAsyncApiSchema>;
  items?: ServerAsyncApiSchema;
  example?:
    | {
        [key: string]: object;
      }
    | string;
  required?: string[];
  minimum?: number;
  maximum?: number;
  $ref?: string;
}
