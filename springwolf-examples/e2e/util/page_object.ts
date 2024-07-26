/* SPDX-License-Identifier: Apache-2.0 */
import { Locator, Page } from "@playwright/test";

export function locateChannels(locator: Page | Locator) {
  return locator.locator("app-channels > mat-accordion");
  // return locator.locator("section#channels article"); // for new ui
}

export function locateChannelItems(locator: Page | Locator) {
  return locator.locator("app-channels > mat-accordion > mat-expansion-panel");
  // return locator.locator("section#channels article > mat-card"); // for new ui
}

export function locateChannel(
  locator: Page | Locator,
  protocol: string,
  channelName: string,
  action: string,
  payload: string
) {
  return locator.getByTestId(
    // return locator.locator( // for new ui
    "channel-" + protocol + "-" + channelName + "-" + action + "-" + payload
  );
}

export function locatePublishButton(locator: Locator) {
  return locator.getByRole("button", { name: "Publish", exact: true });
}

export function locateSnackbar(locator: Page | Locator) {
  return locator.locator("simple-snack-bar");
}
