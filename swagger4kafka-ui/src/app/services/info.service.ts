import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BASE_URL} from '../shared/global';
import {Info} from '../shared/info';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InfoService {

  constructor(private http: HttpClient) { }

  getInfo(): Observable<Info> {
    return this.http
      .get(BASE_URL + '/info')
      .pipe(map(info => info as Info));
  }

}
