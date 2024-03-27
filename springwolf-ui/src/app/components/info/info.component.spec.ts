/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { InfoComponent } from "./info.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { of } from "rxjs/internal/observable/of";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";

describe("InfoComponent", function () {
  let component: InfoComponent;
  let fixture: ComponentFixture<InfoComponent>;

  let mockedAsyncApiService!: { getAsyncApi: jest.Mock };

  let info = {
    title: "title",
    version: "1.0.0",
    description: "example",
    contact: {
      url: "https://test.com",
      email: {
        name: "springwolf",
        href: "link",
      },
    },
    license: {
      name: "Apache License 2.0",
    },
    asyncApiJson: null,
  };

  beforeEach(() => {
    mockedAsyncApiService = {
      getAsyncApi: jest.fn(),
    };

    TestBed.configureTestingModule({
      declarations: [InfoComponent],
      imports: [MatChipsModule, MarkdownModule.forRoot()],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(InfoComponent as any);
    component = fixture.debugElement.componentInstance;
    component.info = info;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should render the title", async () => {
    mockedAsyncApiService.getAsyncApi.mockReturnValue(of());

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("h1").textContent).toContain("title");
    expect(compiled.querySelector("h5").textContent).toContain(
      " API version 1.0.0 - Download AsyncAPI file"
    );
  });

  it("should render the license information", async () => {
    mockedAsyncApiService.getAsyncApi.mockReturnValue(of());

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("p").textContent).toContain(
      "License: Apache License 2.0 https://test.com  springwolf "
    );
  });
});
