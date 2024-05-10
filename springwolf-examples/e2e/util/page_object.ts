/* SPDX-License-Identifier: Apache-2.0 */
import { Locator, Page } from "@playwright/test";

export function locateChannels(locator: Page | Locator) {
  return locator.locator("app-channels > mat-accordion");
}

export function locateChannelItems(locator: Page | Locator) {
  return locator.locator("app-channels > mat-accordion > mat-expansion-panel");
}

export function locatePublishButton(locator: Locator) {
  return locator.getByRole("button", { name: "Publish" });
}

export function locateSnackbar(locator: Page | Locator) {
  return locator.locator("simple-snack-bar");
}
