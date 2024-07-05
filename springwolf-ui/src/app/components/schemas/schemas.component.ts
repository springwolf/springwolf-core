/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { Location } from "@angular/common";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Schema } from "../../models/schema.model";

@Component({
  selector: "app-schemas",
  templateUrl: "./schemas.component.html",
  styleUrls: ["./schemas.component.css"],
})
export class SchemasComponent implements OnInit {
  schemas: Map<string, Schema> = new Map<string, Schema>();
  selectedSchema: string = "";

  constructor(
    private asyncApiService: AsyncApiService,
    private location: Location
  ) {
    this.setSchemaSelectionFromLocation();
  }

  ngOnInit(): void {
    this.location.subscribe((): void => this.setSchemaSelectionFromLocation());
    this.asyncApiService
      .getAsyncApi()
      .subscribe((asyncapi) => (this.schemas = asyncapi.components.schemas));
  }

  setSchemaSelection(schema: Schema): void {
    if (schema.anchorIdentifier) {
      window.location.hash = schema.anchorIdentifier;
    }
  }
  setSchemaSelectionFromLocation(): void {
    this.selectedSchema = window.location.hash;
  }
}
