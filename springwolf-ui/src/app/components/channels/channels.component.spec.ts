/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelsComponent } from "./channels.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { MatAccordion } from "@angular/material/expansion";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";
import { MaterialModule } from "../../material.module";
import { Operation } from "../../models/operation.model";
import { Component, Input } from "@angular/core";

@Component({
  selector: "app-channel-main",
  template: "",
})
export class MockChannelMainComponent {
  @Input() channelName: string;
  @Input() operation: Operation;
}

describe("ChannelsComponent", () => {
  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelsComponent, {
      imports: [MaterialModule],
      declarations: [MockChannelMainComponent],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen.getByText("Channels")).toBeTruthy();
  });
});
