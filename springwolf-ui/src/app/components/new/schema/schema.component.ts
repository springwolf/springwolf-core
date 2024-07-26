/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";
import { Schema } from "../../../models/schema.model";
import { last } from "rxjs";

@Component({
  selector: "app-schema-new",
  templateUrl: "./schema.component.html",
  styleUrls: ["./schema.component.css"],
})
export class SchemaNewComponent {
  schema = input.required<Schema>();
  protected readonly Object = Object;
}
