/* SPDX-License-Identifier: Apache-2.0 */
import { test, expect } from "@playwright/test";
import {
  monitorDockerLogs,
  MonitorDockerLogsResponse,
} from "../util/external_process";
import {
  locateChannel,
  locateChannelItems,
  locatePublishButton,
  locateSnackbar,
} from "../util/page_object";
import { getExampleAsyncApi, getExampleProject } from "../util/example";

test.describe("Publishing for " + getExampleProject() + " plugin", () => {
  test.skip(
    ["cloud-stream", "sns"].includes(getExampleProject()),
    "Plugin does not support publishing"
  );

  let dockerLogs: MonitorDockerLogsResponse;
  test.beforeAll(async () => {
    dockerLogs = monitorDockerLogs();
  });

  test.beforeEach(async ({ page }) => {
    await page.goto("");

    console.log("---\nProcessMessages---\n", dockerLogs.messages.join("\n"));
    dockerLogs.clearMessages();
    dockerLogs.log = true;
  });

  test.afterAll(async () => {
    expect(dockerLogs.errors).toHaveLength(0);
  });

  test("shows success notification when publishing", async ({ page }) => {
    const channelEntry = locateChannelItems(page).first();
    await channelEntry.click();

    const button = locatePublishButton(channelEntry);
    await button.click();

    const snackBar = locateSnackbar(page);
    await expect(snackBar).toContainText("Example payload sent");
  });

  const operations = getExampleAsyncApi().operations;
  Object.keys(operations).forEach((key: string) => {
    const operation = operations[key];
    const action = operation.action;
    const protocol = Object.keys(operation.bindings)[0];
    const channelName = operation.channel.$ref.split("/").pop();
    const payload = operation.messages[0].$ref
      .split("/")
      .pop()
      .split(".")
      .pop();

    if (
      payload === "AnotherPayloadAvroDto" ||
      payload === "XmlPayloadDto" ||
      payload === "YamlPayloadDto" ||
      payload === "MonetaryAmount" ||
      payload === "StringConsumer$StringEnvelope" ||
      payload === "ExamplePayloadProtobufDto$Message"
    ) {
      return; // publishing is not possible for these
    }

    test(channelName + " with " + payload, async ({ page }) => {
      const channel = locateChannel(
        page,
        protocol,
        channelName,
        action,
        payload
      );
      await channel.click();

      const button = locatePublishButton(channel);
      await button.click();

      await expect
        .poll(
          async () =>
            dockerLogs.messages
              .filter((m) => m.includes("Publishing to"))
              .filter((m) => m.includes(channelName))
              .filter((m) => m.includes(payload)).length
        )
        .toBeGreaterThanOrEqual(1);

      if (action === "receive") {
        await expect
          .poll(
            async () =>
              dockerLogs.messages
                .filter((m) => m.includes("Received new message in"))
                .filter((m) => m.includes(channelName)).length
          )
          .toBeGreaterThanOrEqual(1);
      }
    });
  });
});
