/* SPDX-License-Identifier: Apache-2.0 */
import { enableProdMode } from "@angular/core";
import { platformBrowserDynamic } from "@angular/platform-browser-dynamic";
import { AppModule } from "./app/app.module";
import { environment } from "./environments/environment";

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .catch((err) => console.error(err));

// TODO: as casting (as string)
// TODO: verify all !! casting
// TODO: verify if Example field can be mandatory
// TODO: verify anchorIdentifier -> deepLink
