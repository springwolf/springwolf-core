/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UiService {
  public static readonly DEFAULT_NEW_UI = true;
  public static readonly DEFAULT_SHOW_BINDINGS = true;
  public static readonly DEFAULT_SHOW_HEADERS = true;

  private _isNewUi = new BehaviorSubject<boolean>(UiService.DEFAULT_NEW_UI);
  isNewUi$ = this._isNewUi.asObservable();

  private _isShowBindings = new BehaviorSubject<boolean>(
    UiService.DEFAULT_SHOW_BINDINGS
  );
  isShowBindings$ = this._isShowBindings.asObservable();

  private _isShowHeaders = new BehaviorSubject<boolean>(
    UiService.DEFAULT_SHOW_HEADERS
  );
  isShowHeaders$ = this._isShowHeaders.asObservable();

  toggleIsNewUi(value: boolean) {
    this._isNewUi.next(value);
  }

  toggleIsShowBindings(value: boolean) {
    this._isShowBindings.next(value);
  }

  toggleIsShowHeaders(value: boolean) {
    this._isShowHeaders.next(value);
  }
}
