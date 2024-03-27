/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ChannelMainComponent } from "./channel-main.component";
import { PublisherService } from "../../../service/publisher.service";
import { MatDivider } from "@angular/material/divider";
import { MatTab, MatTabGroup, MatTabHeader } from "@angular/material/tabs";
import { JsonComponent } from "../../json/json.component";
import { MarkdownModule } from "ngx-markdown";

describe("ChannelMainComponent", () => {
  let component: ChannelMainComponent;
  let fixture: ComponentFixture<ChannelMainComponent>;

  let mockedAsyncApiService = {
    getAsyncApi: jest.fn(),
  };
  let mockedPublisherService = {
    getAsyncApi: jest.fn(),
  };

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        ChannelMainComponent,
        JsonComponent,
        MatDivider,
        MatTabGroup,
        MatTab,
        MatTabHeader,
      ],
      imports: [MarkdownModule.forRoot()],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
        { provide: PublisherService, useValue: mockedPublisherService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ChannelMainComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });
});
