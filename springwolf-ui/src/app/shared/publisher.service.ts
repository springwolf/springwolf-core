 
import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class PublisherService {

  constructor(private http: HttpClient) { }

  publishToKafka(topic: string, payload: object): Observable<unknown> {
    const params = new HttpParams().set('topic', topic);
    return this.http.post('/asyncapi/kafka/publish', payload, { params });
  }

}