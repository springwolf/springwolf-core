/* SPDX-License-Identifier: Apache-2.0 */
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from "@angular/common/http";
import { importProvidersFrom, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { InMemoryWebApiModule } from "angular-in-memory-web-api";
import { environment } from "./../environments/environment";
import { AppComponent } from "./app.component";
import { ChannelMainComponent } from "./components/channels/channel-main/channel-main.component";
import { ChannelMainComponent as ChannelMainComponentNew } from "./components/new/channels/channel-main/channel-main.component";
import { ChannelsComponent } from "./components/channels/channels.component";
import { ChannelsComponent as ChannelsComponentNew } from "./components/new/channels/channels.component";
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
import {
  INotificationService,
  NotificationService,
} from "./service/notification.service";
import { FormsModule } from "@angular/forms";
import { JsonComponent } from "./components/json/json.component";
import { AsyncApiMapperService } from "./service/asyncapi/asyncapi-mapper.service";
import { MarkdownModule, provideMarkdown } from "ngx-markdown";
import { UiService } from "./service/ui.service";
import { provideAnimationsAsync } from "@angular/platform-browser/animations/async";

export const declarations = [
  AppComponent,
  HeaderComponent,
  InfoComponent,
  ServersComponent,
  ChannelsComponent,
  ChannelsComponentNew,
  ChannelMainComponent,
  ChannelMainComponentNew,
  SchemasComponent,
  SchemaComponent,
  SchemaRangeComponent,
  JsonComponent,
];
export const imports = [
  BrowserModule,
  MaterialModule,
  FormsModule,
  MarkdownModule.forRoot(),
];
export const providers = [
  // provideExperimentalZonelessChangeDetection(),
  provideAnimationsAsync(),
  provideAnimationsAsync(),
  provideHttpClient(withInterceptorsFromDi()),
  environment.production
    ? []
    : importProvidersFrom(
        InMemoryWebApiModule.forRoot(MockServer, { delay: 100 })
      ),
  provideMarkdown(),

  AsyncApiService,
  AsyncApiMapperService,
  { provide: INotificationService, useClass: NotificationService },
  PublisherService,
  UiService,
];

export const ngModule = {
  declarations: declarations,
  imports: imports,
  providers: providers,
};

@NgModule({
  declarations: declarations,
  imports: imports,
  providers: providers,
  bootstrap: [AppComponent],
})
export class AppModule {}
