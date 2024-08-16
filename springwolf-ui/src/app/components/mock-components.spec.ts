/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";
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
  data = input.required<any>();
}

@Component({ selector: "app-schema", template: "" })
export class MockAppSchema {
  schema = input.required<Schema>();
}

@Component({ selector: "app-channel-main", template: "" })
export class MockChannelMainComponent {
  channelName = input.required<string>();
  operation = input.required<Operation>();
}

@Component({ selector: "app-channel-operation", template: "" })
export class MockChannelOperationComponent {
  channelName = input.required<string>();
  operation = input.required<Operation>();
}

@Component({ selector: "app-prism-editor", template: "" })
export class MockPrismEditorComponent {
  code = input<string>("");
}

@Component({ selector: "app-schema-new", template: "" })
export class MockAppSchemaNewComponent {
  schema = input.required<Schema>();
}

@Component({ selector: "mat-icon", template: "" })
export class MockMatIcon {
  svgIcon = input.required<string>();
}
