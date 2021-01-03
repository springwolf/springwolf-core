
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class PublisherService {

  constructor(private http: HttpClient) { }

  publish(protocol: string, topic: string, payload: object): Observable<unknown> {
    const url = `/asyncapi/${protocol}/publish`;
    const params = new HttpParams().set('topic', topic);
    console.log(`Publishing to ${url}`);
    return this.http.post(url, payload, { params });
  }

}
