/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerUiConfig {
  initialConfig: {
    showBindings: boolean;
    showHeaders: boolean;
  };
  groups: {
    name: string;
  }[];
}
