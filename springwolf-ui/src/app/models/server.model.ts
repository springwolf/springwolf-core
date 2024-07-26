/* SPDX-License-Identifier: Apache-2.0 */
export const SERVER_ANCHOR_PREFIX = "server-";
export interface Server {
  host: string;
  protocol: string;

  anchorIdentifier: string;
  anchorUrl: string;
}
