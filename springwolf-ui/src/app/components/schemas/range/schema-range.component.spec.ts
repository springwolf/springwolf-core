/* SPDX-License-Identifier: Apache-2.0 */
import { ComponentFixture, TestBed } from "@angular/core/testing";
import { SchemaRangeComponent } from "./schema-range.component";

describe("SchemaRangeComponent", function () {
  let component: SchemaRangeComponent;
  let fixture: ComponentFixture<SchemaRangeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SchemaRangeComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SchemaRangeComponent as any);
    component = fixture.debugElement.componentInstance;
  });

  it("should create the component", () => {
    expect(component).toBeTruthy();
  });

  it("should have `( 0.1 .. 10 )` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: true,
      exclusiveMaximum: true,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain(
      "( 0.1 .. 10 )"
    );
  });

  it("should have `[ 0.1 .. 10 )` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: false,
      exclusiveMaximum: true,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain(
      "[ 0.1 .. 10 )"
    );
  });

  it("should have `( 0.1 .. 10 ]` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
      maximum: 10,
      exclusiveMinimum: true,
      exclusiveMaximum: false,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain(
      "( 0.1 .. 10 ]"
    );
  });

  it("should have `[ 0.1 .. 10 ]` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
      maximum: 10,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain(
      "[ 0.1 .. 10 ]"
    );
  });

  it("should have `> 0.1` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
      exclusiveMinimum: true,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain("> 0.1");
  });

  it("should have `< 10` as value", async () => {
    component.schema = {
      title: "test",
      maximum: 10,
      exclusiveMaximum: true,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain("< 10");
  });

  it("should have `>= 0.1` as value", async () => {
    component.schema = {
      title: "test",
      minimum: 0.1,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain(">= 0.1");
  });

  it("should have `<= 10` as value", async () => {
    component.schema = {
      title: "test",
      maximum: 10,
    };

    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector("span").textContent).toContain("<= 10");
  });
});
