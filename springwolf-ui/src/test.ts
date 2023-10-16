/* SPDX-License-Identifier: Apache-2.0 */
import "zone.js/dist/zone-testing";
import { getTestBed } from "@angular/core/testing";
import {
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting,
} from "@angular/platform-browser-dynamic/testing";

// First, initialize the Angular testing environment.
getTestBed().initTestEnvironment(
  BrowserDynamicTestingModule,
  platformBrowserDynamicTesting()
);
