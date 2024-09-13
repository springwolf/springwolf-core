/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelMainComponent } from "./channel-main.component";
import { MarkdownModule } from "ngx-markdown";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../../service/mock/mock-asyncapi.service";
import { MockAppJson, MockAppSchema } from "../../mock-components.spec";
import { MaterialModule } from "../../../material.module";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { PublisherService } from "../../../service/publisher.service";

describe("ChannelMainComponent", () => {
  const mockData = mockedExampleSchemaMapped.channelOperations
    .slice(-1)
    .pop()!!;

  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelMainComponent, {
      declarations: [MockAppJson, MockAppSchema],
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
    expect(screen.getByText("Example")).toBeTruthy();
    expect(screen.getByText("Message Binding")).toBeTruthy();
  });
});
