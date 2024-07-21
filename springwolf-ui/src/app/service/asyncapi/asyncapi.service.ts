/* SPDX-License-Identifier: Apache-2.0 */
import { AsyncApi } from "../../models/asyncapi.model";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, shareReplay } from "rxjs";
import { filter, map } from "rxjs/operators";
import { EndpointService } from "../endpoint.service";
import { AsyncApiMapperService } from "./asyncapi-mapper.service";
import { ServerAsyncApi } from "./models/asyncapi.model";

@Injectable()
export class AsyncApiService {
  private readonly docs: Observable<AsyncApi>;

  constructor(
    private http: HttpClient,
    private asyncApiMapperService: AsyncApiMapperService
  ) {
    this.docs = this.http.get<ServerAsyncApi>(EndpointService.docs).pipe(
      map((item) => {
        return this.asyncApiMapperService.toAsyncApi(item);
      }),
      filter((item): item is AsyncApi => item !== undefined),
      shareReplay()
    );
  }

  public getAsyncApi(): Observable<AsyncApi> {
    return this.docs;
  }
}
