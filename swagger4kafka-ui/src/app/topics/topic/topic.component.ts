import {Component, Input, OnInit} from '@angular/core';
import {KafkaEndpoint} from '../../shared/kafka-endpoint.model';
import {Model} from '../../shared/model.model';
import {PublisherService} from '../../services/publisher.service';
import {ModelsService} from '../../services/models.service';
import {Example} from './example';
import {Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged} from 'rxjs/operators';

@Component({
  selector: 'app-topic',
  templateUrl: './topic.component.html',
  styleUrls: ['./topic.component.css'],
  providers: [
    ModelsService,
    PublisherService
  ]
})
export class TopicComponent implements OnInit {
  @Input() endpoint: KafkaEndpoint;
  model: Model;
  defaultExample: Example;
  isOpen = false;

  isValidExample: boolean;
  invalidMessage: string;

  changeExample = new Subject<string>();

  constructor(
    private modelsService: ModelsService,
    private publisherService: PublisherService,
  ) { }

  ngOnInit() {
    this.modelsService
      .getModels()
      .subscribe(models => this.model = models[this.endpoint.payloadModelName]);

    this.defaultExample = new Example(this.endpoint.payloadExample);
    this.validate(this.defaultExample.value);

    this.changeExample
      .pipe(debounceTime(500))
      .pipe(distinctUntilChanged())
      .subscribe(value => this.validate(value));
  }

  validate(payloadString: string): void {
    let json;
    try {
      json = JSON.parse(payloadString);
    } catch (e) {
      this.isValidExample = false;
      this.invalidMessage = 'Failed to parse example';
      return;
    }

    const payload = {
      className: this.endpoint.payloadClassName,
      object: json
    };

    this.publisherService
      .validate(this.endpoint.topic, payload)
      .subscribe(response => {
        this.isValidExample = response['valid'];
        this.invalidMessage = response['message'];
      });
  }

  publish(payloadString: string): void {
    const payload = {
      className: this.endpoint.payloadClassName,
      object: JSON.parse(payloadString)
    };

    this.publisherService
      .publish(this.endpoint.topic, payload)
      .subscribe(
        _ => _,
        error => alert(error['message'])
      );
  }

}
