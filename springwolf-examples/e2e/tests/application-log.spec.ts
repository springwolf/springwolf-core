/* SPDX-License-Identifier: Apache-2.0 */
import { test, expect } from "@playwright/test";
import {
  monitorDockerLogs,
  MonitorDockerLogsResponse,
  verifyNoErrorLogs,
} from "../util/external_process";

let dockerLogs: MonitorDockerLogsResponse;
test.beforeAll(async () => {
  dockerLogs = monitorDockerLogs();
});
test.afterAll(async () => {
  console.debug("---\nProcessMessages---\n", dockerLogs.messages.join("\n"));
});

test("no error nor warn log messages in logs", async ({ page }) => {
  // Ensure that logs are available by calling the docs endpoint
  await page.goto("");

  verifyNoErrorLogs(dockerLogs);
  expect(dockerLogs.errors).toHaveLength(0);
});
