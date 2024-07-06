/* SPDX-License-Identifier: Apache-2.0 */
import { SchemaComponent } from "./schema.component";
import { SchemaRangeComponent } from "../range/schema-range.component";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";
import { Example } from "../../../models/example.model";
import { JsonComponent } from "../../json/json.component";
import { render, screen } from "@testing-library/angular";

describe("SchemaComponent", () => {
  beforeEach(async () => {
    const mockedSchemaRangeComponent = jest.fn();

    await render(SchemaComponent, {
      declarations: [SchemaComponent, SchemaRangeComponent, JsonComponent],
      imports: [MatChipsModule, MarkdownModule.forRoot()],
      providers: [
        { provide: SchemaRangeComponent, useValue: mockedSchemaRangeComponent },
      ],
      componentInputs: {
        schema: {
          title: "String",
          type: "string",
          example: new Example("example value"),
        },
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
