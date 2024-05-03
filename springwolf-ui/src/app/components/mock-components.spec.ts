/* SPDX-License-Identifier: Apache-2.0 */
import { Component, Input } from "@angular/core";
import { Schema } from "../models/schema.model";
import { Operation } from "../models/operation.model";

/*
 * This file contains all mock components for usage in tests.
 */
describe("MockTest", () => {
  it("basic test, since at least one test is required 1 + 1 = 2", () => {
    expect(1 + 1).toBe(2);
  });
});

@Component({ selector: "app-json", template: "" })
export class MockAppJson {
  @Input() data: any;
}

@Component({ selector: "app-schema", template: "" })
export class MockAppSchema {
  @Input() schema: Schema;
}

@Component({ selector: "app-channel-main", template: "" })
export class MockChannelMainComponent {
  @Input() channelName: string;
  @Input() operation: Operation;
}
