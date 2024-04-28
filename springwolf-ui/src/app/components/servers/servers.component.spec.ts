/* SPDX-License-Identifier: Apache-2.0 */
import { render, screen } from "@testing-library/angular";
import { ServersComponent } from "./servers.component";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";
import { MaterialModule } from "../../material.module";

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

  it("should create the component", () => {
    expect(screen.getByText("Servers")).toBeTruthy();
  });
});
