/* SPDX-License-Identifier: Apache-2.0 */

export interface ServerBindings {
  [protocol: string]: ServerBinding;
}

export interface ServerBinding {
  [bindingProperty: string]: string | ServerBinding;
}
