export interface ServerAsyncApiSchema {
  description?: string;
  type: string;
  format: string;
  enum: string[];
  properties?: Map<string, ServerAsyncApiSchema>;
  items?: ServerAsyncApiSchema;
  example?: {
    [key: string]: object;
  } | string;
  required?: string[];
  $ref?: string;
}
