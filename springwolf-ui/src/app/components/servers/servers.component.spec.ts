/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ServersComponent } from "./servers.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";

describe("ServerComponent", () => {
  let component: ServersComponent;
  let fixture: ComponentFixture<ServersComponent>;

  let mockedAsyncApiService!: { getAsyncApi: jest.Mock };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServersComponent],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ServersComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });
});
