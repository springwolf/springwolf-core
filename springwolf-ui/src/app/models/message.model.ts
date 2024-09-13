/* SPDX-License-Identifier: Apache-2.0 */
import { Binding } from "./bindings.model";
import { Schema, SchemaRef } from "./schema.model";

export interface Message {
  name: string;
  title: string;
  description?: string;
  contentType: string;
  /** Schemas of primitive types are inlined, others referenced */
  payload: SchemaRef | Schema;
  headers: SchemaRef;
  bindings: Map<string, Binding>;
  rawBindings: { [protocol: string]: object };
}
