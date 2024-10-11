/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelsNewComponent } from "./channels.component";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../../service/mock/mock-asyncapi.service";
import { MaterialModule } from "../../../material.module";
import {
  MockChannelOperationComponent,
  MockPrismEditorComponent,
} from "../../mock-components.spec";

describe("ChannelsNewComponent", () => {
  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelsNewComponent, {
      imports: [MaterialModule],
      declarations: [MockChannelOperationComponent, MockPrismEditorComponent],
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
