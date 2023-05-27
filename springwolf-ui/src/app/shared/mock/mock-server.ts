import { InMemoryDbService, RequestInfo, STATUS } from 'angular-in-memory-web-api';
import mockSpringwolfApp from './mock.springwolf-app.json';
import mockSpringwolfAmqp from './mock.springwolf-amqp-example.json';
import mockSpringwolfCloudStream from './mock.springwolf-cloud-stream-example.json';
import mockSpringwolfKafka from './mock.springwolf-kafka-example.json';

export class MockServer implements InMemoryDbService {
  createDb() {
    return {kafka: []};
  }

  get(reqInfo: RequestInfo) {
    console.log("Returning mock data")

    if (reqInfo.req.url.endsWith('/docs')) {
      const body = this.selectMockData()
      return reqInfo.utils.createResponse$(() => {
        return {
          status: STATUS.OK,
          body: body
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

  private selectMockData() {
    const hostname = window.location.hostname;

    if(hostname.includes("app")) {
      return mockSpringwolfApp;
    } else if(hostname.includes("amqp")) {
      return mockSpringwolfAmqp;
    } else if(hostname.includes("cloud-stream")) {
      return mockSpringwolfCloudStream;
    }
    // Kafka is default
    return mockSpringwolfKafka;
  }
}
