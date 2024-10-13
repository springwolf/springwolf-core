/* SPDX-License-Identifier: Apache-2.0 */
import { test, expect } from "@playwright/test";
import {
  monitorDockerLogs,
  MonitorDockerLogsResponse,
  verifyNoErrorLogs,
} from "../util/external_process";
import {
  locateChannel,
  locateChannelItems,
  locatePublishButton,
  locateSnackbar,
} from "../util/page_object";
import { getExampleAsyncApi, getExampleProject } from "../util/example";

let dockerLogs: MonitorDockerLogsResponse;
test.describe("Publishing in " + getExampleProject() + " example", () => {
  test.skip(
    ["cloud-stream", "sns", "stomp"].includes(getExampleProject()),
    "Example/Plugin does not support publishing"
  );

  test.beforeAll(async () => {
    dockerLogs = monitorDockerLogs();
  });

  test.beforeEach(async ({ page }) => {
    await page.goto("");

    dockerLogs.log = true;
  });

  test.afterEach(async () => {
    verifyNoErrorLogs(dockerLogs);

    console.debug("---\nProcessMessages---\n", dockerLogs.messages.join("\n"));
  });

  test("shows success notification when publishing", async ({ page }) => {
    const channelEntry = locateChannelItems(page).first();
    await channelEntry.click();

    const button = locatePublishButton(channelEntry);
    await button.click();

    const snackBar = locateSnackbar(page);
    await expect(snackBar).toContainText("Example payload sent");
  });

  testPublishingEveryChannelItem();
});

function testPublishingEveryChannelItem() {
  const asyncApiDoc = getExampleAsyncApi();
  const operations = asyncApiDoc.operations;
  Object.keys(operations).forEach((key: string) => {
    const operation = operations[key];
    operation.messages.forEach((messageReference) => {
      const action = operation.action;
      const protocol = Object.keys(operation.bindings)[0];
      const channelName = operation.channel.$ref.split("/").pop();
      const messageName = messageReference.$ref.split("/").pop();
      const messageTitle: string =
        asyncApiDoc.components.messages[messageName]?.title;
      const payloadName = messageName;

      if (
        messageTitle === "AnotherPayloadAvroDto" || // Avro publishing is not supported
        messageTitle === "XmlPayloadDto" || // Unable to create correct xml payload
        messageTitle === "YamlPayloadDto" || // Unable to create correct yaml payload
        messageTitle === "MonetaryAmount" || // Issue with either MonetaryAmount of ModelConverters
        messageTitle === "Message" || // Unable to instantiate ExamplePayloadProtobufDto$Message class
        messageTitle === "VehicleBase" || // Unable to publish abstract class for discriminator demo
        messageTitle.startsWith("GenericPayload") || // Unable to publish generic payload (amqp)
        channelName === "CRUD-topic-exchange-2" || // Publishing through amqp exchange is not supported, see GH-366
        channelName === "example-topic-exchange_example-topic-routing-key" // Publishing through amqp exchange is not supported, see GH-366
      ) {
        return; // skip
      }

      test(
        action + " " + channelName + " with " + messageTitle,
        async ({ page }) => {
          const channel = locateChannel(
            page,
            protocol,
            channelName,
            action,
            messageTitle
          );
          await channel.click();

          const button = locatePublishButton(channel);
          await button.click();

          await verifyMessageIsPublished();
          if (action === "receive") {
            await verifyMessageIsReceived();
          }

          async function verifyMessageIsPublished() {
            await expect
              .poll(
                async () => {
                  const found = dockerLogs.messages
                    .filter((m) => m.includes("Publishing to"))
                    .filter((m) => m.includes(channelName))
                    .filter((m) => m.includes(payloadName)).length;
                  console.debug(
                    `Polling for publish message and found=${found}`
                  );
                  return found;
                },
                {
                  message: `Expected publishing message in application logs in channel ${channelName} with type ${payloadName}`,
                }
              )
              .toBeGreaterThanOrEqual(1);
          }
          async function verifyMessageIsReceived() {
            await expect
              .poll(
                async () => {
                  const found = dockerLogs.messages
                    .filter((m) => m.includes("Received new message in"))
                    .filter((m) => m.includes(channelName)).length;
                  console.debug(
                    `Polling for receive message and found=${found}`
                  );
                  return found;
                },
                {
                  message: `Expected receiving message in application logs in channel ${channelName}`,
                  timeout: 10_000,
                }
              )
              .toBeGreaterThanOrEqual(1);
          }
        }
      );
    });
  });
}
