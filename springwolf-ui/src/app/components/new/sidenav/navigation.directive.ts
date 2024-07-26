/* SPDX-License-Identifier: Apache-2.0 */
import { Directive, ElementRef } from "@angular/core";

@Directive({
  selector: "[appNavigationTarget]",
})
export class NavigationTargetDirective {
  constructor(public el: ElementRef) {}
}
