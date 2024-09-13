/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelOperationComponent } from "./channel-operation.component";
import { AsyncApiService } from "../../../../service/asyncapi/asyncapi.service";
import { PublisherService } from "../../../../service/publisher.service";
import { MaterialModule } from "../../../../material.module";
import { MarkdownModule } from "ngx-markdown";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../../../service/mock/mock-asyncapi.service";
import {
  MockAppJson,
  MockAppSchemaNewComponent,
  MockPrismEditorComponent,
} from "../../../mock-components.spec";

describe("ChannelOperationComponent", () => {
  const mockData = mockedExampleSchemaMapped.channelOperations
    .slice(-1)
    .pop()!!;

  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelOperationComponent, {
      declarations: [
        MockAppJson,
        MockAppSchemaNewComponent,
        MockPrismEditorComponent,
      ],
      imports: [MaterialModule, MarkdownModule.forRoot()],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
        { provide: PublisherService, useValue: {} },
      ],
      componentInputs: {
        channelName: mockData.name,
        operation: mockData.operation,
      },
    });
  });

  it("should render the component and data", () => {
    expect(screen.getByText(mockData.operation.channelName)).toBeTruthy();
    expect(screen.getByText(mockData.operation.message.title)).toBeTruthy();
    expect(
      screen.getByText(mockData.operation.message.contentType)
    ).toBeTruthy();
    expect(screen.getByText(mockData.operation.servers[0].name)).toBeTruthy();
  });
});
