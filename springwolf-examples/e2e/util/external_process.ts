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
      console.log(strData);
    }
  });

  process.stderr.on("data", (data) => {
    const strData = data.toString().split("\n") as string[];
    response.errors.push(...strData);

    if (response.log) {
      console.log(strData);
    }
  });

  process.on("close", (code) => {
    console.error("Child exited with", code, "and stdout has been saved");
  });

  return response;
}

export function verifyNoErrorLogs(dockerLogs: MonitorDockerLogsResponse) {
  const errorMessages = dockerLogs.messages
    .filter((message) => message.includes("i.g.s")) // io.github.springwolf
    .filter((message) => message.includes("ERROR") || message.includes("WARN"));

  expect(errorMessages, {
    message: "Unexpected Springwolf ERROR or WARN log messages found",
  }).toHaveLength(0);
}
