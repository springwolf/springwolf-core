/* SPDX-License-Identifier: Apache-2.0 */
import { ApplicationConfig, importProvidersFrom } from "@angular/core";
import { provideAnimationsAsync } from "@angular/platform-browser/animations/async";
import {
  provideHttpClient,
  withInterceptorsFromDi,
} from "@angular/common/http";
import { environment } from "../environments/environment";
import { InMemoryWebApiModule } from "angular-in-memory-web-api";
import { MockServer } from "./service/mock/mock-server";
import { provideMarkdown } from "ngx-markdown";
import { AssetService, IAssetService } from "./service/asset.service";
import { AsyncApiService } from "./service/asyncapi/asyncapi.service";
import { AsyncApiMapperService } from "./service/asyncapi/asyncapi-mapper.service";
import {
  INotificationService,
  NotificationService,
} from "./service/notification.service";
import { PublisherService } from "./service/publisher.service";
import { IUiService, UiService } from "./service/ui.service";

export const appConfig: ApplicationConfig = {
  providers: [
    // provideExperimentalZonelessChangeDetection(),
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
    { provide: IUiService, useClass: UiService },
  ],
};
