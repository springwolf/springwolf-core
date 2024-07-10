/* SPDX-License-Identifier: Apache-2.0 */
import { ServerBindings } from "./bindings.model";

export interface ServerChannels {
  [key: string]: ServerChannel;
}

export interface ServerChannel {
  description?: string;
  messages: {
    [key: string]: {
      $ref: string;
    };
  };
  servers?: {
    $ref: string;
  }[];
  bindings?: ServerBindings;
}
