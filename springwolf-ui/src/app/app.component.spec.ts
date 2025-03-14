/* SPDX-License-Identifier: Apache-2.0 */
import { AppComponent } from "./app.component";
import { render, screen, waitFor, within } from "@testing-library/angular";
import { appConfig } from "./app.config";
import { MatIconTestingModule } from "@angular/material/icon/testing";

describe("AppComponent", () => {
  test("should render", async () => {
    // given
    jest.spyOn(console, "info").mockImplementation(() => {});

    // when
    await render(AppComponent, {
      providers: appConfig.providers,
      imports: [MatIconTestingModule],
    });

    // then
    await waitFor(() => {
      expect(
        screen.getAllByText("Springwolf example project", { exact: false })
          .length
      ).toBeGreaterThan(0);
    });

    // get menu items
    const articles = screen.getAllByRole("article");

    // server menu
    const servers = articles.find(
      (article) => within(article).queryByText("Servers") !== null
    );
    expect(servers).not.toBeNull();
    expect(within(servers!!).getAllByRole("article").length).toBeGreaterThan(0);

    // channel menu
    const channels = articles.find(
      (article) => within(article).queryByText("Channels") !== null
    );
    expect(channels).not.toBeNull();
    expect(within(channels!!).getAllByRole("article").length).toBeGreaterThan(
      0
    );

    // schema menu
    const schemas = articles.find(
      (article) => within(article).queryByText("Schemas") !== null
    );
    expect(schemas).not.toBeNull();
    expect(within(schemas!!).getAllByRole("article").length).toBeGreaterThan(0);
  });
});
