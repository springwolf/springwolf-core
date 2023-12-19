/* SPDX-License-Identifier: Apache-2.0 */
export interface ServerServers {
  [server: string]: {
    host: string;
    protocol: string;
    description?: string;
  };
}
