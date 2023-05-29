import { AsyncApi } from './models/asyncapi.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Endpoints } from './endpoints';
import {AsyncApiMapperService, ServerAsyncApi} from "./asyncapi-mapper.service";

@Injectable()
export class AsyncApiService {

    private docs: AsyncApi;

    constructor(private http: HttpClient, private asyncApiMapperService: AsyncApiMapperService) {
    }

    public getAsyncApi(): Observable<AsyncApi> {
        if (this.docs) {
            return of(this.docs);
        }

        return this.http.get<ServerAsyncApi>(Endpoints.docs).pipe(map(item => {
            this.docs = this.asyncApiMapperService.toAsyncApi(item);
            return this.docs;
        }));
    }
}
