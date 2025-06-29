/* SPDX-License-Identifier: Apache-2.0 */
import { Component, OnInit, signal } from "@angular/core";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { Server } from "../../models/server.model";
import { MatCardModule } from "@angular/material/card";
import { CommonModule } from "@angular/common";
import { NavigationTargetDirective } from "../sidenav/navigation.directive";

@Component({
  selector: "app-servers",
  templateUrl: "./servers.component.html",
  styleUrls: ["./servers.component.css"],
  imports: [CommonModule, MatCardModule, NavigationTargetDirective],
})
export class ServersComponent implements OnInit {
  servers = signal<Map<string, Server>>(new Map<string, Server>());

  constructor(private asyncApiService: AsyncApiService) {}

  ngOnInit(): void {
    this.asyncApiService
      .getAsyncApi()
      .subscribe((asyncapi) => this.servers.set(asyncapi.servers));
  }
}
