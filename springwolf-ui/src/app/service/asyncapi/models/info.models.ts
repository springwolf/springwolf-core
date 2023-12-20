/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerAsyncApiInfo {
  title: string;
  version: string;
  description?: string;
  // TODO: use license
  license?: {
    name?: string;
    url?: string;
  };
}
