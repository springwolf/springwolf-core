/* SPDX-License-Identifier: Apache-2.0 */
import { Example } from "./example.model";

export interface Schema {
  name?: string;
  title: string;
  description?: string;

  refName?: string;
  refTitle?: string;
  anchorIdentifier?: string;
  anchorUrl?: string;

  type?: string;
  // type == object
  properties?: Map<string, Schema>;
  // type == array
  items?: Schema;

  format?: string;
  required?: string[];
  enum?: string[];
  example?: Example;
  minimum?: number;
  maximum?: number;
  exclusiveMinimum?: boolean;
  exclusiveMaximum?: boolean;
}
