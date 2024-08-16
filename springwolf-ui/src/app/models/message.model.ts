/* SPDX-License-Identifier: Apache-2.0 */
import { Binding } from "./bindings.model";

export interface Message {
  name: string;
  title: string;
  description?: string;
  contentType: string;
  payload: {
    name: string;
    title: string;
    anchorUrl: string;
  };
  headers: {
    name: string;
    title: string;
    anchorUrl: string;
  };
  bindings: Map<string, Binding>;
  rawBindings: { [protocol: string]: object };
}
