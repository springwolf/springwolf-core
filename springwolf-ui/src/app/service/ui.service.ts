/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UiService {
  public static readonly DEFAULT_SHOW_BINDINGS = true;
  public static readonly DEFAULT_SHOW_HEADERS = true;

  private _isShowBindings = new BehaviorSubject<boolean>(
    UiService.DEFAULT_SHOW_BINDINGS
  );
  isShowBindings$ = this._isShowBindings.asObservable();

  private _isShowHeaders = new BehaviorSubject<boolean>(
    UiService.DEFAULT_SHOW_HEADERS
  );
  isShowHeaders$ = this._isShowHeaders.asObservable();

  toggleIsShowBindings(value: boolean) {
    this._isShowBindings.next(value);
  }

  toggleIsShowHeaders(value: boolean) {
    this._isShowHeaders.next(value);
  }
}
