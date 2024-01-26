/* SPDX-License-Identifier: Apache-2.0 */
export interface Info {
  title: string;
  version: string;
  description?: string;
  contact: {
    url?: string;
    email?: {
      name: string;
      href: string;
    };
  };
  license: {
    name?: string;
    url?: string;
  };
  asyncApiJson: object;
}
