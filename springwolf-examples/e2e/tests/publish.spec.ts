/* SPDX-License-Identifier: Apache-2.0 */
import { test, expect } from "@playwright/test";
import {
  monitorDockerLogs,
  MonitorDockerLogsResponse,
} from "../util/external_process";
import {
  locateChannelItems,
  locateChannels,
  locatePublishButton,
  locateSnackbar,
} from "../util/page_object";

let process: MonitorDockerLogsResponse;
test.beforeAll(async () => {
  process = monitorDockerLogs();
});

test.beforeEach(async ({ page }) => {
  await page.goto("");

  console.log("---\nProcessMessages---\n", process.messages.join("\n"));
  process.clearMessages();
  process.log = true;
});

test.afterAll(async () => {
  expect(process.errors).toHaveLength(0);
});

test("can publish and receive with backend", async ({ page }) => {
  const channelEntries = await locateChannelItems(page).all();

  const index = 0;

  const channelEntry = channelEntries[index];
  await channelEntry.click();

  const button = locatePublishButton(channelEntry);
  await button.click();

  await expect
    .poll(
      async () =>
        process.messages.filter((m) => m.includes("Publishing to")).length
    )
    .toBeGreaterThanOrEqual(1);
  await expect
    .poll(
      async () =>
        process.messages.filter((m) => m.includes("Received new message"))
          .length
    )
    .toBeGreaterThanOrEqual(1);
});
