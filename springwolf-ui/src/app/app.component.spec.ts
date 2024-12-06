/* SPDX-License-Identifier: Apache-2.0 */
import { AppComponent } from "./app.component";
import { render, screen } from "@testing-library/angular";


describe("AppComponent", () => {
  test("should render", async () => {
    await render(AppComponent);

    expect(screen.getByText("Springwolf"));
  });
});
