/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { SchemasComponent } from "./schemas.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { MatAccordion } from "@angular/material/expansion";

describe("SchemasComponent", () => {
  let component: SchemasComponent;
  let fixture: ComponentFixture<SchemasComponent>;

  let mockedAsyncApiService = {
    getAsyncApi: jest.fn(),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemasComponent, MatAccordion],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(SchemasComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });
});
