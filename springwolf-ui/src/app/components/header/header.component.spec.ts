/* SPDX-License-Identifier: Apache-2.0 */
import { MatToolbarModule } from "@angular/material/toolbar";
import { HeaderComponent } from "./header.component";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { render } from "@testing-library/angular";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";

describe("HeaderComponent", () => {
  beforeEach(async () => {
    await render(HeaderComponent, {
      imports: [MatToolbarModule, MatSlideToggleModule],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();
  });
});
