/* SPDX-License-Identifier: Apache-2.0 */
import { MatToolbarModule } from "@angular/material/toolbar";
import { HeaderComponent } from "./header.component";
import { render, screen, fireEvent } from "@testing-library/angular";
import { AsyncApiService } from "../../service/asyncapi/asyncapi.service";
import { mockedAsyncApiService } from "../../service/mock/mock-asyncapi.service";
import { MatMenuModule } from "@angular/material/menu";
import { IAssetService } from "../../service/asset.service";
import { mockedAssetService } from "../../service/mock/mock-asset.service";
import {
  mockedUiService,
  uiConfigSubject,
} from "../../service/mock/mock-ui.service";
import { MockMatIcon } from "../mock-components.spec";
import { IUiService } from "../../service/ui.service";

describe("HeaderComponent", () => {
  beforeEach(async () => {
    await render(HeaderComponent, {
      declarations: [MockMatIcon],
      imports: [MatToolbarModule, MatMenuModule],
      providers: [
        { provide: IUiService, useValue: mockedUiService },
        { provide: IAssetService, useValue: mockedAssetService },
        { provide: AsyncApiService, useValue: mockedAsyncApiService },
      ],
    });
  });

  it("should create the component", () => {
    expect(screen).toBeTruthy();

    expect(mockedAssetService.load).toHaveBeenCalled();

    // by default, no group menu is shown
    expect(screen.queryByTestId("settings")).toBeDefined();
    expect(screen.queryByTestId("settings-group-menu")).toBeNull();
  });

  it("should show groups when the uiConfig is updated", () => {
    uiConfigSubject.next({
      groups: [{ name: "group1" }, { name: "group2" }],
    });

    // open group menu
    const settingButton = screen.getByTestId("settings");
    fireEvent.click(settingButton);
    const groupMenu = screen.getByTestId("settings-group-menu");
    fireEvent.click(groupMenu);

    // then has options
    expect(screen.getByText("default")).toBeTruthy();
    expect(screen.getByText("group1")).toBeTruthy();
    expect(screen.getByText("group2")).toBeTruthy();

    // when selecting a group, changeGroup is called
    fireEvent.click(screen.getByText("group1"));
    expect(mockedUiService.changeGroup).toHaveBeenCalledWith("group1");
  });
});
