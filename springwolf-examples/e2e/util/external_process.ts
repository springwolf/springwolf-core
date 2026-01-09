/* SPDX-License-Identifier: Apache-2.0 */
import { spawn } from "child_process";
import { getExampleProject } from "./example";
import { expect } from "@playwright/test";

export interface MonitorDockerLogsResponse {
  messages: string[];
  errors: string[];
  log: boolean;
  clearMessages: () => void;
}

export function monitorDockerLogs(): MonitorDockerLogsResponse {
  const process = spawn("docker", ["compose", "logs", "-f", "app"], {
    cwd: "../springwolf-" + getExampleProject() + "-example",
  });

  const response: MonitorDockerLogsResponse = {
    messages: [] as string[],
    errors: [] as string[],
    log: false,
    clearMessages: () => {
      response.messages.splice(0, response.messages.length);
    },
  };

  process.stdout.on("data", (data) => {
    const strData = data.toString().split("\n") as string[];
    response.messages.push(...strData);

    if (response.log) {
      console.debug(strData);
    }
  });

  process.stderr.on("data", (data) => {
    const strData = data.toString().split("\n") as string[];
    response.errors.push(...strData);

    if (response.log) {
      console.debug(strData);
    }
  });

  process.on("close", (code) => {
    console.error("Child exited with ", code, " and stdout has been saved");
  });

  return response;
}

export function verifyNoErrorLogs(dockerLogs: MonitorDockerLogsResponse) {
  expect(dockerLogs.errors).toHaveLength(0);

  const errorMessages = dockerLogs.messages
    .filter((message) => message.includes("i.g.s")) // io.github.springwolf
    .filter(
      (message) =>
        message.includes("FATAL") ||
        message.includes("ERROR") ||
        message.includes("WARN") ||
        message.includes("INFO") ||
        message.includes("Failed")
    );

  expect(errorMessages, {
    message: "expect: No Springwolf FATAL, ERROR, WARN or INFO log messages found",
  }).toHaveLength(0);
}
