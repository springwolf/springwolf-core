/* SPDX-License-Identifier: Apache-2.0 */
import { AppComponent } from "./app.component";
import { render, screen } from "@testing-library/angular";
import { ngModule } from "./app.module";

describe("AppComponent", () => {
  test("should render", async () => {
    await render(AppComponent, ngModule);

    expect(screen.getByText("Springwolf"));
  });
});
