import { Injectable } from '@angular/core';
import {Model} from '../shared/model.model';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {BASE_URL} from '../shared/global';

@Injectable()
export class ModelsService {

  constructor(private http: HttpClient) { }

  getModels(): Observable<{ [key: string]: Model }> {
    const url = BASE_URL + '/models';
    return this.http
      .get(url)
      .pipe(map(item => item as { [key: string]: Model }));
  }

}
