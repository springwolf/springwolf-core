import { Component, OnInit } from '@angular/core';
import { Subscription, SubscriptionLike } from 'rxjs';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Schema } from '../shared/models/schema.model';

@Component({
  selector: 'app-schemas',
  templateUrl: './schemas.component.html',
  styleUrls: ['./schemas.component.css']
})
export class SchemasComponent implements OnInit {


  schemas: Map<string, Schema>;
  schemaRoot = "properties";
  nameSubscription: Subscription;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => this.schemas = asyncapi.get(name).components.schemas);
    });
  }

}
