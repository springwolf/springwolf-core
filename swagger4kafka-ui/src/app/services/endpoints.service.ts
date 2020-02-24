import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {KafkaEndpoint} from '../shared/kafka-endpoint.model';
import {BASE_URL} from '../shared/global';

@Injectable()
export class EndpointsService {

  constructor(private http: HttpClient) { }

  getKakfaEndpoints(): Observable<KafkaEndpoint[]> {
    const url = BASE_URL + '/endpoints';
    return this.http
      .get(url)
      .pipe(map(item => item as KafkaEndpoint[]));
  }

}
