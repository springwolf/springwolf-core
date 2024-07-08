/* SPDX-License-Identifier: Apache-2.0 */
import { SchemaNewComponent } from "./schema.component";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";
import { Example } from "../../../models/example.model";
import { JsonComponent } from "../../json/json.component";
import { render, screen } from "@testing-library/angular";
import { SchemaRangeComponent } from "../../schemas/range/schema-range.component";
import { Schema } from "../../../models/schema.model";

describe("SchemaNewComponent", () => {
  beforeEach(async () => {
    const mockedSchemaRangeComponent = jest.fn();

    await render(SchemaNewComponent, {
      declarations: [SchemaNewComponent, SchemaRangeComponent, JsonComponent],
      imports: [MatChipsModule, MarkdownModule.forRoot()],
      providers: [
        { provide: SchemaRangeComponent, useValue: mockedSchemaRangeComponent },
      ],
      componentInputs: {
        schema: {
          title: "String",
          type: "string",
          example: new Example("example value"),
        } as Schema,
      },
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();
  });

  it("should render primitive string example", async () => {
    expect(screen.getByText(/example value/i)).toBeTruthy();
  });
});
