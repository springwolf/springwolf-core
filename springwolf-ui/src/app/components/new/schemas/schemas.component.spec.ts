/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { SchemasNewComponent } from "./schemas.component";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { MatAccordion } from "@angular/material/expansion";

describe("SchemasComponent", () => {
  let component: SchemasNewComponent;
  let fixture: ComponentFixture<SchemasNewComponent>;

  let mockedAsyncApiService = {
    getAsyncApi: jest.fn(),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MatAccordion],
      declarations: [SchemasNewComponent],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(SchemasNewComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });
});
