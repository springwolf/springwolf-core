/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input, Input } from "@angular/core";
import { Schema } from "../../../models/schema.model";
import { Example } from "../../../models/example.model";
import { initSchema } from "../../../service/mock/init-values";
import { last } from "rxjs";

@Component({
  selector: "app-schema-new",
  templateUrl: "./schema.component.html",
  styleUrls: ["./schema.component.css"],
})
export class SchemaNewComponent {
  schema = input.required<Schema>();
  protected readonly last = last;
}
