/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelsComponent } from "./channels.component";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../../service/mock/mock-asyncapi.service";
import { MaterialModule } from "../../../material.module";
import { MockChannelMainComponent } from "../../mock-components.spec";

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

  it("should render the component and data", () => {
    expect(screen.getByText("Channels")).toBeTruthy();

    mockedExampleSchemaMapped.channelOperations.forEach((channelOperation) => {
      expect(screen.getAllByText(channelOperation.name)[0]).toBeTruthy();
      expect(
        screen.getAllByText(channelOperation.operation.message.title)[0]
      ).toBeTruthy();
    });
  });
});
