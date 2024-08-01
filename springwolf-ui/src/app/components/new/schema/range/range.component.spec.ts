/* SPDX-License-Identifier: Apache-2.0 */
import { RangeNewComponent as RangeComponent } from "./range.component";
import { render, screen } from "@testing-library/angular";

describe("SchemaRangeNewComponent", function () {
  it("should create the component", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        upperBound: 10,
        lowerBoundInclusive: false,
        upperBoundInclusive: false,
      },
    });

    expect(screen).toBeTruthy();
  });

  it("should have `( 0.1 .. 10 )` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        upperBound: 10,
        lowerBoundInclusive: false,
        upperBoundInclusive: false,
      },
    });

    expect(screen.getByText("( 0.1 .. 10 )")).toBeTruthy();
  });

  it("should have `[ 0.1 .. 10 )` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        upperBound: 10,
        lowerBoundInclusive: true,
        upperBoundInclusive: false,
      },
    });

    expect(screen.getByText("[ 0.1 .. 10 )")).toBeTruthy();
  });

  it("should have `( 0.1 .. 10 ]` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        upperBound: 10,
        lowerBoundInclusive: false,
        upperBoundInclusive: true,
      },
    });

    expect(screen.getByText("( 0.1 .. 10 ]")).toBeTruthy();
  });

  it("should have `[ 0.1 .. 10 ]` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        upperBound: 10,
      },
    });

    expect(screen.getByText("[ 0.1 .. 10 ]")).toBeTruthy();
  });

  it("should have `> 0.1` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
        lowerBoundInclusive: false,
      },
    });

    expect(screen.getByText("> 0.1")).toBeTruthy();
  });

  it("should have `< 10` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        upperBound: 10,
        upperBoundInclusive: false,
      },
    });

    expect(screen.getByText("< 10")).toBeTruthy();
  });

  it("should have `>= 0.1` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        lowerBound: 0.1,
      },
    });

    expect(screen.getByText(">= 0.1")).toBeTruthy();
  });

  it("should have `<= 10` as value", async () => {
    await render(RangeComponent, {
      componentInputs: {
        upperBound: 10,
      },
    });

    expect(screen.getByText("<= 10")).toBeTruthy();
  });
});
