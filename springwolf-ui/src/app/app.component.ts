/* SPDX-License-Identifier: Apache-2.0 */
import { Component } from "@angular/core";
import { MaterialModule } from "./material.module";
import { FormsModule } from "@angular/forms";
import { MarkdownModule } from "ngx-markdown";
import { SchemasComponent } from "./components/schemas/schemas.component";
import { InfoComponent } from "./components/info/info.component";
import { ServersComponent } from "./components/servers/servers.component";
import { ChannelsComponent } from "./components/channels/channels.component";
import { SidenavComponent } from "./components/sidenav/sidenav.component";
import { HeaderComponent } from "./components/header/header.component";
import { CommonModule } from "@angular/common";
import { NavigationTargetDirective } from "./components/sidenav/navigation.directive";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    MarkdownModule,
    HeaderComponent,
    SidenavComponent,
    InfoComponent,
    ServersComponent,
    ChannelsComponent,
    SchemasComponent,
    NavigationTargetDirective,
  ],
})
export class AppComponent {}
