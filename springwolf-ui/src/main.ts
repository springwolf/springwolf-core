/* SPDX-License-Identifier: Apache-2.0 */
import { bootstrapApplication } from "@angular/platform-browser";
import { enableProdMode } from "@angular/core";
import { platformBrowserDynamic } from "@angular/platform-browser-dynamic";
import { AppModule } from "./app/app.module";
import { appConfig } from "./app/app.config";
import { AppComponent } from "./app/app.component";
import { environment } from "./environments/environment";

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule);

// bootstrapApplication(AppComponent, appConfig)
//   .catch((err) => console.error(err));
