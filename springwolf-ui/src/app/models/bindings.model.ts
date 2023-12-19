/* SPDX-License-Identifier: Apache-2.0 */
export interface Bindings {
  [protocol: string]: Binding;
}

export interface Binding {
  [protocol: string]: string | Binding;
}
