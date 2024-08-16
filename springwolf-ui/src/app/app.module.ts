/* SPDX-License-Identifier: Apache-2.0 */
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from "@angular/common/http";
import { importProvidersFrom, NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { InMemoryWebApiModule } from "angular-in-memory-web-api";
import { environment } from "../environments/environment";
import { AppComponent } from "./app.component";
import { ChannelMainComponent } from "./components/channels/channel-main/channel-main.component";
import { ChannelOperationComponent } from "./components/new/channels/channel-main/channel-operation.component";
import { ChannelsComponent } from "./components/channels/channels.component";
import { ChannelsNewComponent } from "./components/new/channels/channels.component";
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
import { SidenavComponent } from "./components/new/sidenav/sidenav.component";
import { NavigationTargetDirective } from "./components/new/sidenav/navigation.directive";
import { PrismEditorComponent } from "./components/new/code/prism-editor.component";
import { SchemaNewComponent } from "./components/new/schema/schema.component";
import { ServersNewComponent } from "./components/new/servers/servers.component";
import { SchemasNewComponent } from "./components/new/schemas/schemas.component";
import { RangeNewComponent } from "./components/new/schema/range/range.component";
import { AssetService, IAssetService } from "./service/asset.service";

@NgModule({
  imports: [],
  declarations: [NavigationTargetDirective],
  exports: [NavigationTargetDirective],
})
export class DirectivesModule {}

export const declarations = [
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
  PrismEditorComponent,
  SidenavComponent,
  ServersNewComponent,
  ChannelsNewComponent,
  ChannelOperationComponent,
  SchemasNewComponent,
  SchemaNewComponent,
  RangeNewComponent,
];
export const imports = [
  DirectivesModule,
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

  { provide: IAssetService, useClass: AssetService },
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
  imports: [imports],
  providers: providers,
  bootstrap: [AppComponent],
})
export class AppModule {}
