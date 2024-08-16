/* SPDX-License-Identifier: Apache-2.0 */
import { MatToolbarModule } from "@angular/material/toolbar";
import { HeaderComponent } from "./header.component";
import { MatSlideToggleModule } from "@angular/material/slide-toggle";
import { render } from "@testing-library/angular";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";
import { MatMenuModule } from "@angular/material/menu";
import { IAssetService } from "../../service/asset.service";
import { mockedAssetService } from "../../service/mock/mock-asset.service";
import { MockMatIcon } from "../mock-components.spec";

describe("HeaderComponent", () => {
  beforeEach(async () => {
    await render(HeaderComponent, {
      declarations: [MockMatIcon],
      imports: [MatToolbarModule, MatSlideToggleModule, MatMenuModule],
      providers: [
        { provide: IAssetService, useValue: mockedAssetService },
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();

    expect(mockedAssetService.load).toHaveBeenCalled();
  });
});
