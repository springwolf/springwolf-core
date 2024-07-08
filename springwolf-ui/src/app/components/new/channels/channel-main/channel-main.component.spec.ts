/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelMainNewComponent } from "./channel-main.component";
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

describe("ChannelMainNewComponent", () => {
  const mockData = mockedExampleSchemaMapped.channelOperations[0];

  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelMainNewComponent, {
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
    expect(
      screen.getByText(mockData.operation.message.description!!)
    ).toBeTruthy();
  });
});
