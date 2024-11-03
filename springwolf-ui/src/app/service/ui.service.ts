/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, shareReplay } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { ServerUiConfig } from "./asyncapi/models/ui.model";
import { EndpointService } from "./endpoint.service";

export abstract class IUiService {
  public static readonly DEFAULT_SHOW_BINDINGS = true;
  public static readonly DEFAULT_SHOW_HEADERS = true;
  public static readonly DEFAULT_GROUP = "default";

  abstract isGroup$: Observable<string>;
  abstract isShowBindings$: Observable<boolean>;
  abstract isShowHeaders$: Observable<boolean>;
  abstract uiConfig: Observable<ServerUiConfig>;

  abstract toggleIsShowBindings(value: boolean): void;
  abstract toggleIsShowHeaders(value: boolean): void;
  abstract changeGroup(value: string): void;
}

@Injectable()
export class UiService extends IUiService {
  private _getGroup = new BehaviorSubject<string>(IUiService.DEFAULT_GROUP);
  readonly isGroup$ = this._getGroup.asObservable();

  private _isShowBindings = new BehaviorSubject<boolean>(
    IUiService.DEFAULT_SHOW_BINDINGS
  );
  readonly isShowBindings$ = this._isShowBindings.asObservable();

  private _isShowHeaders = new BehaviorSubject<boolean>(
    IUiService.DEFAULT_SHOW_HEADERS
  );
  readonly isShowHeaders$ = this._isShowHeaders.asObservable();

  readonly uiConfig: Observable<ServerUiConfig>;

  constructor(private http: HttpClient) {
    super();

    this.uiConfig = this.http
      .get<ServerUiConfig>(EndpointService.uiConfig)
      .pipe(shareReplay());
  }

  toggleIsShowBindings(value: boolean) {
    this._isShowBindings.next(value);
  }

  toggleIsShowHeaders(value: boolean) {
    this._isShowHeaders.next(value);
  }

  changeGroup(value: string) {
    this._getGroup.next(value);
  }
}
