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
import { ChannelOperationComponent } from "./components/channels/channel-main/channel-operation.component";
import { HeaderComponent } from "./components/header/header.component";
import { InfoComponent } from "./components/info/info.component";
import { MaterialModule } from "./material.module";
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
import { SidenavComponent } from "./components/sidenav/sidenav.component";
import { NavigationTargetDirective } from "./components/sidenav/navigation.directive";
import { PrismEditorComponent } from "./components/code/prism-editor.component";
import { SchemaComponent } from "./components/schema/schema.component";
import { RangeComponent } from "./components/schema/range/range.component";
import { AssetService, IAssetService } from "./service/asset.service";
import { SchemasComponent } from "./components/schemas/schemas.component";
import { ServersComponent } from "./components/servers/servers.component";
import { ChannelsComponent } from "./components/channels/channels.component";

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
  SchemasComponent,
  SchemaComponent,
  JsonComponent,
  PrismEditorComponent,
  SidenavComponent,
  ChannelOperationComponent,
  RangeComponent,
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
