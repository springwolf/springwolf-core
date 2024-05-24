/* SPDX-License-Identifier: Apache-2.0 */
import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable, map, share } from "rxjs";
import { EndpointService } from "./endpoint.service";

@Injectable()
export class PublisherService {
  constructor(private http: HttpClient) {}
  publishable: { [key: string]: Observable<boolean> } = {};

  canPublish(protocol: string): Observable<boolean> {
    if (this.publishable[protocol] === undefined) {
      this.publishable[protocol] = this.http
        .get<undefined>(EndpointService.getPublishEndpoint(protocol), {
          observe: "response",
        })
        .pipe(
          map((response: any) => response.status === 200),
          share()
        );
    }

    return this.publishable[protocol];
  }

  publish(
    protocol: string,
    topic: string,
    payload: string,
    payloadType: string,
    headers: object,
    bindings: object
  ): Observable<unknown> {
    const url = EndpointService.getPublishEndpoint(protocol);
    const params = new HttpParams().set("topic", topic);
    const body = {
      payload,
      payloadType,
      headers,
      bindings,
    };
    console.log(
      `Publishing to ${url} with messageBinding ${JSON.stringify(
        bindings
      )} and headers ${JSON.stringify(headers)}: ${JSON.stringify(body)}`
    );
    return this.http.post(url, body, { params });
  }
}
