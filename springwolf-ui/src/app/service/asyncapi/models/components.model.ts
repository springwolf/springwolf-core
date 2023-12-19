/* SPDX-License-Identifier: Apache-2.0 */
import { ServerAsyncApiSchema } from "./schema.model";

export interface ServerComponents {
  schemas: Map<string, ServerAsyncApiSchema>;
}
