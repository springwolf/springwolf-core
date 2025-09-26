/* SPDX-License-Identifier: Apache-2.0 */
import { Example } from "./example.model";

export interface SchemaRef {
  ts_type: "ref";

  name: string;
  title: string;
  anchorUrl: string;
}

export interface Schema {
  ts_type: "object";

  /**
   * Fully qualified schema name
   */
  name: string;
  /**
   * Short schema name
   */
  title: string;
  anchorIdentifier: string;
  anchorUrl: string;
  usedBy: { name: string; anchorUrl: string; type: "channel" | "schema" }[];
  description?: string;
  deprecated?: boolean;

  enum?: string[];
  example?: Example;

  type?: string | string[];
  format?: string;
  // type == ref
  refAnchorUrl?: string;
  refName?: string;
  refTitle?: string;
  // type == object
  properties?: { [key: string]: Schema };
  required?: string[];
  // type == array
  items?: Schema;
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
  exclusiveMinimum?: boolean;
  exclusiveMaximum?: boolean;
  multipleOf?: number;
}
