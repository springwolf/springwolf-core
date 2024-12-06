/* SPDX-License-Identifier: Apache-2.0 */
import {Component, input} from "@angular/core";
import {Schema} from "../../models/schema.model";
import "../code/prism.bridge";
import {RangeComponent} from "./range/range.component";
import {MarkdownModule} from "ngx-markdown";
import {MatChipsModule} from "@angular/material/chips";
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import {MatIconModule} from "@angular/material/icon";
import {CommonModule, KeyValuePipe, NgTemplateOutlet} from "@angular/common";

@Component({
  selector: "app-schema",
  templateUrl: "./schema.component.html",
  styleUrls: ["./schema.component.css"],
  imports: [
    MatSelectModule,
    MatListModule,
    MatChipsModule,
    MatIconModule,
    CommonModule,
    MarkdownModule,
    RangeComponent,
  ]
})
export class SchemaComponent {
  schema = input.required<Schema>();
  protected readonly Object = Object;
}
