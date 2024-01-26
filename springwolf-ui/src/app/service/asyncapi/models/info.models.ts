/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerAsyncApiInfo {
  title: string;
  version?: string;
  description?: string;
  contact?: {
    name?: string;
    url?: string;
    email?: string;
  };
  license?: {
    name?: string;
    url?: string;
  };
}
