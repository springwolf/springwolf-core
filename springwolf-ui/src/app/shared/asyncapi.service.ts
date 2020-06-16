import { AsyncApi } from './models/asyncapi.model';

export class AsyncApiService {

    public getAsyncApi(): AsyncApi {
        return {
            info: {
                title: 'Example AsyncAPI Document',
                version: '1.0.0',
                description: 'This is an example of an async API document.'
            }
        }
    }

}