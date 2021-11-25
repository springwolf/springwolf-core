import { InMemoryDbService, RequestInfo, STATUS } from 'angular-in-memory-web-api';
import mockAsyncApi from './mock.json';

export class MockServer implements InMemoryDbService {
  createDb() {
    return {kafka: []};
  }

  get(reqInfo: RequestInfo) {
    console.log("Returning mock data")
    if (reqInfo.req.url.endsWith('/docs')) {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: mockAsyncApi
        }
      });
    }

    return undefined;
  }

  post(reqInfo: RequestInfo) {
    if (reqInfo.req.url.endsWith('/publish')) {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK
        }
      })
    }

    return undefined;
  }

}
