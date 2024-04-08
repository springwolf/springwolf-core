/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ChannelsComponent } from "./channels.component";
import { MatAccordion } from "@angular/material/expansion";

describe("ChannelsComponent", () => {
  let component: ChannelsComponent;
  let fixture: ComponentFixture<ChannelsComponent>;

  let mockedAsyncApiService = {
    getAsyncApi: jest.fn(),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [MatAccordion],
      declarations: [ChannelsComponent],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ChannelsComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });
});
