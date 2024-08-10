/* SPDX-License-Identifier: Apache-2.0 */
import { SchemaRangeComponent } from "./schema-range.component";
import { render, screen } from "@testing-library/angular";
import { Schema } from "../../../models/schema.model";

describe("SchemaRangeComponent", function () {
  const renderComponent = async (schema: Schema) => {
    await render(SchemaRangeComponent, {
      componentInputs: {
        schema: schema,
      },
    });
  };

  it("should create the component", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: true,
      exclusiveMaximum: true,
    });

    expect(screen).toBeTruthy();
  });

  it("should have `( 0.1 .. 10 )` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: true,
      exclusiveMaximum: true,
    });

    expect(screen.getByText("( 0.1 .. 10 )")).toBeTruthy();
  });

  it("should have `[ 0.1 .. 10 )` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: false,
      exclusiveMaximum: true,
    });

    expect(screen.getByText("[ 0.1 .. 10 )")).toBeTruthy();
  });

  it("should have `( 0.1 .. 10 ]` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: true,
      exclusiveMaximum: false,
    });

    expect(screen.getByText("( 0.1 .. 10 ]")).toBeTruthy();
  });

  it("should have `[ 0.1 .. 10 ]` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      maximum: 10,
    });

    expect(screen.getByText("[ 0.1 .. 10 ]")).toBeTruthy();
  });

  it("should have `> 0.1` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
      exclusiveMinimum: true,
    });

    expect(screen.getByText("> 0.1")).toBeTruthy();
  });

  it("should have `< 10` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      maximum: 10,
      exclusiveMaximum: true,
    });

    expect(screen.getByText("< 10")).toBeTruthy();
  });

  it("should have `>= 0.1` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      minimum: 0.1,
    });

    expect(screen.getByText(">= 0.1")).toBeTruthy();
  });

  it("should have `<= 10` as value", async () => {
    await renderComponent({
      title: "test",
      name: "test",
      anchorIdentifier: "test",
      usedBy: [],
      maximum: 10,
    });

    expect(screen.getByText("<= 10")).toBeTruthy();
  });
});
