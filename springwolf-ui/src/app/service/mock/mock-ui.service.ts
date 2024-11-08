/* SPDX-License-Identifier: Apache-2.0 */
import { Observable, Subject } from "rxjs";
import { IUiService } from "../ui.service";
import { ServerUiConfig } from "../asyncapi/models/ui.model";

export const uiConfigSubject = new Subject<ServerUiConfig>();

export const mockedUiService: IUiService = {
  isGroup$: new Observable<string>(),
  isShowBindings$: new Observable<boolean>(),
  isShowHeaders$: new Observable<boolean>(),
  uiConfig: uiConfigSubject.asObservable(),

  toggleIsShowBindings: jest.fn(),
  toggleIsShowHeaders: jest.fn(),
  changeGroup: jest.fn(),
};
