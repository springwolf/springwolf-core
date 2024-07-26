/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class UiService {
  private _isNewUi = new BehaviorSubject<boolean>(false);
  isNewUi$ = this._isNewUi.asObservable();

  toggleIsNewUi(value: boolean) {
    this._isNewUi.next(value);
  }
}
