/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { Schema } from "../../../models/schema.model";

@Component({
  selector: "app-schemas-new",
  templateUrl: "./schemas.component.html",
  styleUrls: ["./schemas.component.css"],
})
export class SchemasNewComponent implements OnInit {
  schemas: Map<string, Schema> = new Map<string, Schema>();

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService
      .getAsyncApi()
      .subscribe((asyncapi) => (this.schemas = asyncapi.components.schemas));
  }
}
