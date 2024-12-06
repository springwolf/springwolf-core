/* SPDX-License-Identifier: Apache-2.0 */
import {Component, input} from "@angular/core";
import {CommonModule} from "@angular/common";

@Component({
  selector: "app-schema-range",
  templateUrl: "./range.component.html",
  imports: [
    CommonModule,
  ]
})
export class RangeComponent {
  lowerBound = input<number>();
  upperBound = input<number>();
  lowerBoundInclusive = input<boolean, boolean | string>(true, {
    transform: (value: boolean | string) => value === true || value == "true",
  });
  upperBoundInclusive = input<boolean, boolean | string>(true, {
    transform: (value: boolean | string) => value === true || value == "true",
  });
}
