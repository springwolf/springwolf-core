/* SPDX-License-Identifier: Apache-2.0 */
import { SchemaComponent } from "./schema.component";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";
import { Example } from "../../models/example.model";
import { JsonComponent } from "../json/json.component";
import { render, screen } from "@testing-library/angular";
import { MatDividerModule } from "@angular/material/divider";
import { RangeComponent } from "./range/range.component";
import { Schema } from "../../models/schema.model";

describe("SchemaNewComponent", () => {
  beforeEach(async () => {
    const mockedSchemaRangeComponent = jest.fn();

    const schema: Schema = {
      ts_type: "object",
      name: "ExampleSchema",
      title: "String",
      type: "string",
      example: new Example("example value"),
      enum: ["enum value1", "enum value2"],

      anchorIdentifier: "anchor-example-schema",
      anchorUrl: "#anchor-example-schema",
      usedBy: [],
    };

    await render(SchemaComponent, {
      declarations: [SchemaComponent, RangeComponent, JsonComponent],
      imports: [MatChipsModule, MatDividerModule, MarkdownModule.forRoot()],
      providers: [
        { provide: RangeComponent, useValue: mockedSchemaRangeComponent },
      ],
      inputs: {
        schema: schema,
      },
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();
  });

  it("should render primitive string example", async () => {
    expect(screen.getByText(/example value/i)).toBeTruthy();
  });

  it("should render enum values", async () => {
    expect(screen.getByText(/enum value1/i)).toBeTruthy();
    expect(screen.getByText(/enum value2/i)).toBeTruthy();
  });
});
