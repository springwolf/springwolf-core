/* SPDX-License-Identifier: Apache-2.0 */
import { InfoComponent } from "./info.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { of } from "rxjs/internal/observable/of";
import { MatChipsModule } from "@angular/material/chips";
import { MarkdownModule } from "ngx-markdown";
import { render, screen } from "@testing-library/angular";
import { Observable } from "rxjs";
import { Info } from "../../models/info.model";
import { AsyncApi } from "../../models/asyncapi.model";
import { Server } from "../../models/server.model";
import { Schema } from "../../models/schema.model";

describe("InfoComponent", function () {
  let info: Info = {
    title: "title",
    version: "1.0.0",
    description: "example",
    contact: {
      url: "https://test.com",
      email: {
        name: "Springwolf",
        href: "link",
      },
    },
    license: {
      name: "Apache License 2.0",
    },
    asyncApiJson: {},
  };
  const mockedAsyncApiService: { getAsyncApi: () => Observable<AsyncApi> } = {
    getAsyncApi: () =>
      of({
        info: info,
        servers: new Map<string, Server>(),
        channels: [],
        channelOperations: [],
        components: {
          schemas: new Map<string, Schema>(),
        },
        defaultContentType: "application/json",
      }),
  };

  beforeEach(async () => {
    await render(InfoComponent, {
      imports: [MatChipsModule, MarkdownModule.forRoot()],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();
  });

  it("should render the title", async () => {
    expect(screen.getByText("title v1.0.0", { selector: "h1" })).toBeDefined();
  });

  it("should render the license information", async () => {
    expect(screen.queryByText("Apache License 2.0")).toBeTruthy();
    expect(screen.queryByText("https://test.com")).toBeTruthy();
    expect(screen.queryByText("Springwolf")).toBeTruthy();
  });
});
