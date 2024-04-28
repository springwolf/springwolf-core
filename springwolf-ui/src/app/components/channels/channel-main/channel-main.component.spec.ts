/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ChannelMainComponent } from "./channel-main.component";
import { AsyncApiService } from "../../../service/asyncapi/asyncapi.service";
import { PublisherService } from "../../../service/publisher.service";
import { MaterialModule } from "../../../material.module";
import { of } from "rxjs";
import { exampleSchemas } from "../../../service/mock/example-data";
import { AsyncApiMapperService } from "../../../service/asyncapi/asyncapi-mapper.service";
import { MarkdownModule } from "ngx-markdown";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../../service/mock/mock-asyncapi.service";
import { Component, Input } from "@angular/core";
import { Schema } from "../../../models/schema.model";

@Component({ selector: "app-json", template: "" })
export class MockAppJson {
  @Input() data: any;
}

@Component({ selector: "app-schema", template: "" })
export class MockAppSchema {
  @Input() schema: Schema;
}

describe("ChannelMainComponent", () => {
  beforeEach(async () => {
    mockedAsyncApiService.getAsyncApi.mockClear();

    await render(ChannelMainComponent, {
      imports: [MaterialModule, MarkdownModule.forRoot()],
      declarations: [MockAppJson, MockAppSchema],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
        { provide: PublisherService, useValue: {} },
      ],
      componentProperties: {
        channelName: mockedExampleSchemaMapped.channelOperations[0].name,
        operation: mockedExampleSchemaMapped.channelOperations[0].operation,
      },
    });
  });

  it("should create the component", () => {
    expect(screen.getByText("Another payload model")).toBeTruthy();
  });
});
