/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerAsyncApiInfo {
  title: string;
  version?: string;
  description?: string;
  contact?: {
    name?: string;
    url?: string;
    email?: string;

    // allow any additional fields
    [key: string]: unknown;
  };
  license?: {
    name?: string;
    url?: string;

    // allow any additional fields
    [key: string]: unknown;
  };
  termsOfService?: string;

  // allow any additional fields
  [key: string]: unknown;
}
