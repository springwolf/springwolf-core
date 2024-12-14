/* SPDX-License-Identifier: Apache-2.0 */
import { Locator, Page } from "@playwright/test";

export function locateChannels(locator: Page | Locator) {
  return locator.locator("article#channels article");
}

export function locateChannelItems(locator: Page | Locator) {
  return locator.locator("article#channels article > mat-card");
}

export function locateChannel(
  locator: Page | Locator,
  protocol: string,
  channelName: string,
  action: string,
  payload: string
) {
  return locator.locator(
    "#channel-" + protocol + "-" + channelName + "-" + action + "-" + payload
  );
}

export function locatePublishButton(locator: Locator) {
  return locator.getByRole("button", { name: "Publish", exact: true });
}

export function locateSnackbar(locator: Page | Locator) {
  return locator.locator("simple-snack-bar");
}
