import { Component, OnInit } from '@angular/core';
import {KafkaEndpoint} from '../shared/kafka-endpoint.model';
import {EndpointsService} from '../services/endpoints.service';

@Component({
  selector: 'app-topics',
  templateUrl: './topics.component.html',
  providers: [EndpointsService]
})
export class TopicsComponent implements OnInit {

  kafkaEndpoints: KafkaEndpoint[];

  constructor(private endpointsService: EndpointsService) { }

  ngOnInit() {
    this.endpointsService
      .getKakfaEndpoints()
      .subscribe(endpoints => this.kafkaEndpoints = endpoints);
  }

}
