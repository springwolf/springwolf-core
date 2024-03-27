/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";

import { SchemaComponent } from "./schema.component";
import { SchemaRangeComponent } from "../range/schema-range.component";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";
import { Example } from "../../../models/example.model";

describe("SchemaComponent", () => {
  let component: SchemaComponent;
  let fixture: ComponentFixture<SchemaComponent>;

  let mockedSchemaRangeComponent = jest.mock("../range/schema-range.component");

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaComponent, SchemaRangeComponent],
      imports: [MatChipsModule, MarkdownModule.forRoot()],
      providers: [
        { provide: SchemaRangeComponent, useValue: mockedSchemaRangeComponent },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(SchemaComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should render primitive string", async () => {
    component.schema = {
      title: "String",
      type: "string",
      example: new Example("string"),
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector(".type").textContent).toContain("string");
    expect(compiled.querySelector(".example").textContent).toContain(
      "example: string"
    );
  });
});
