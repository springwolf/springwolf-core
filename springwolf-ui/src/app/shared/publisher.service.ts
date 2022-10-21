import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Endpoints } from './endpoints';

@Injectable()
export class PublisherService {

  constructor(private http: HttpClient) { }

  publish(protocol: string, topic: string, payload: object, headers: object): Observable<unknown> {
    const url = Endpoints.getPublishEndpoint(protocol);
    const params = new HttpParams().set('topic', topic);
    const body = {"payload" : payload, "headers" : headers }
    console.log(`Publishing to ${url}`);
    return this.http.post(url, body, { params });
  }

}
