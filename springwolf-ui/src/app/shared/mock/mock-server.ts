import { InMemoryDbService, RequestInfo, STATUS } from 'angular-in-memory-web-api';
import mockSpringwolfAmqp from '../../../../../springwolf-examples/springwolf-amqp-example/src/test/resources/asyncapi.json';
import mockSpringwolfCloudStream from '../../../../../springwolf-examples/springwolf-cloud-stream-example/src/test/resources/asyncapi.json';
import mockSpringwolfKafka from '../../../../../springwolf-examples/springwolf-kafka-example/src/test/resources/asyncapi.json';

const mockAsyncApi = {
  ...mockSpringwolfAmqp,
  ...mockSpringwolfCloudStream,
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
