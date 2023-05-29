import { InMemoryDbService, RequestInfo, STATUS } from 'angular-in-memory-web-api';
import mockSpringwolfApp from './mock.springwolf-app.json';
import mockSpringwolfAmqp from './mock.springwolf-amqp-example.json';
import mockSpringwolfKafka from './mock.springwolf-kafka-example.json';

const mockAsyncApi = {
  ...mockSpringwolfApp,
  ...mockSpringwolfAmqp,
  ...mockSpringwolfKafka,
}

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
          body: mockSpringwolfKafka
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
