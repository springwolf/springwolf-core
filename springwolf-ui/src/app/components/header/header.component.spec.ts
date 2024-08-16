/* SPDX-License-Identifier: Apache-2.0 */
import { MatToolbarModule } from "@angular/material/toolbar";
import { HeaderComponent } from "./header.component";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { render } from "@testing-library/angular";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";
import { MatIconModule } from "@angular/material/icon";
import { MatMenuModule } from "@angular/material/menu";

describe("HeaderComponent", () => {
  beforeEach(async () => {
    await render(HeaderComponent, {
      imports: [
        MatToolbarModule,
        MatSlideToggleModule,
        MatIconModule,
        MatMenuModule,
      ],
      providers: [
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();
  });
});
