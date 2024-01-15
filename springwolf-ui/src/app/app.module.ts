/* SPDX-License-Identifier: Apache-2.0 */
import { HttpClientModule } from "@angular/common/http";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientInMemoryWebApiModule } from "angular-in-memory-web-api";
import { environment } from "./../environments/environment";
import { AppComponent } from "./app.component";
import { ChannelMainComponent } from "./components/channels/channel-main/channel-main.component";
import { ChannelsComponent } from "./components/channels/channels.component";
import { HeaderComponent } from "./components/header/header.component";
import { InfoComponent } from "./components/info/info.component";
import { MaterialModule } from "./material.module";
import { SchemaComponent } from "./components/schemas/schema/schema.component";
import { SchemaRangeComponent } from "./components/schemas/range/schema-range.component";
import { SchemasComponent } from "./components/schemas/schemas.component";
import { ServersComponent } from "./components/servers/servers.component";
import { AsyncApiService } from "./service/asyncapi/asyncapi.service";
import { MockServer } from "./service/mock/mock-server";
import { PublisherService } from "./service/publisher.service";
import { NotificationService } from "./service/notification.service";
import { FormsModule } from "@angular/forms";
import { JsonComponent } from "./components/json/json.component";
import { AsyncApiMapperService } from "./service/asyncapi/asyncapi-mapper.service";
import { MarkdownModule, provideMarkdown } from "ngx-markdown";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    InfoComponent,
    ServersComponent,
    ChannelsComponent,
    ChannelMainComponent,
    SchemasComponent,
    SchemaComponent,
    SchemaRangeComponent,
    JsonComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    MarkdownModule.forRoot(),
    environment.production
      ? []
      : HttpClientInMemoryWebApiModule.forRoot(MockServer, { delay: 100 }),
  ],
  providers: [
    AsyncApiService,
    AsyncApiMapperService,
    NotificationService,
    PublisherService,
    provideMarkdown(),
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
