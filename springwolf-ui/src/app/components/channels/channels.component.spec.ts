/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelsComponent } from "./channels.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../service/mock/mock-asyncapi.service";
import { mockedUiService } from "../../service/mock/mock-ui.service";
import { MaterialModule } from "../../material.module";
import {
  MockChannelOperationComponent,
  MockPrismEditorComponent,
} from "../mock-components.spec";
import { IUiService } from "../../service/ui.service";

describe("ChannelsNewComponent", () => {
  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelsComponent, {
      imports: [MaterialModule],
      declarations: [MockChannelOperationComponent, MockPrismEditorComponent],
      providers: [
        { provide: IUiService, useValue: mockedUiService },
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
