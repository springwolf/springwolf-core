/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Server } from "../../models/server.model";

@Component({
  selector: "app-servers",
  templateUrl: "./servers.component.html",
  styleUrls: ["./servers.component.css"],
})
export class ServersComponent implements OnInit {
  servers: Map<string, Server> = new Map<string, Server>();

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService
      .getAsyncApi()
      .subscribe((asyncapi) => (this.servers = asyncapi.servers));
  }
}
