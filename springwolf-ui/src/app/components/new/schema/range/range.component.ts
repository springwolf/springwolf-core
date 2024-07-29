/* SPDX-License-Identifier: Apache-2.0 */
import { Component, input } from "@angular/core";

@Component({
  selector: "app-schema-range-new",
  templateUrl: "./range.component.html",
})
export class RangeNewComponent {
  lowerBound = input<number>();
  upperBound = input<number>();
  lowerBoundInclusive = input<boolean, boolean | string>(true, {
    transform: (value: boolean | string) => value === true || value == "true",
  });
  upperBoundInclusive = input<boolean, boolean | string>(true, {
    transform: (value: boolean | string) => value === true || value == "true",
  });
}
