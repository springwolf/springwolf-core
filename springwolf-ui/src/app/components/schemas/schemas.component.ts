/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit, signal } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Schema } from "../../models/schema.model";
import { MarkdownComponent } from "ngx-markdown";
import { MatChipsModule } from "@angular/material/chips";
import { MatCardModule } from "@angular/material/card";
import { JsonComponent } from "../json/json.component";
import { SchemaComponent } from "../schema/schema.component";
import { MatIconModule } from "@angular/material/icon";
import { NavigationTargetDirective } from "../sidenav/navigation.directive";

@Component({
  selector: "app-schemas",
  templateUrl: "./schemas.component.html",
  styleUrls: ["./schemas.component.css"],
  imports: [
    MarkdownComponent,
    MatIconModule,
    MatCardModule,
    MatChipsModule,
    JsonComponent,
    SchemaComponent,
    NavigationTargetDirective,
  ],
})
export class SchemasComponent implements OnInit {
  schemas = signal<Schema[]>([]);

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService
      .getAsyncApi()
      .subscribe((asyncapi) =>
        this.schemas.set([...asyncapi.components.schemas.values()])
      );
  }
}
