/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ServersComponent } from "./servers.component";
import {
  mockedAsyncApiService,
  mockedExampleSchemaMapped,
} from "../../service/mock/mock-asyncapi.service";
import { MaterialModule } from "../../material.module";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";

describe("ServerComponent", () => {
  mockedAsyncApiService.getAsyncApi.mockClear();

  beforeEach(async () => {
    await render(ServersComponent, {
      imports: [MaterialModule],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should render the component and data", () => {
    expect(screen.getByText("Servers")).toBeTruthy();

    mockedExampleSchemaMapped.servers.forEach((server, key) => {
      expect(screen.getAllByText(key)[0]).toBeTruthy();
      expect(screen.getAllByText(server.protocol)[0]).toBeTruthy();
      expect(screen.getAllByText(server.host)[0]).toBeTruthy();
    });
  });
});
