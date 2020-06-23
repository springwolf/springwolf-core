import { InMemoryDbService, RequestInfo, STATUS } from 'angular-in-memory-web-api';
import mockAsyncApi from './mock.json';

export class MockServer implements InMemoryDbService {
  createDb() {
    return {};
  }

  get(reqInfo: RequestInfo) {
    console.log("Returning mock data")
    if (reqInfo.req.url === '/asyncapi-docs?format=json') {
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: mockAsyncApi
        }
      });
    }
    return undefined;
  }
}