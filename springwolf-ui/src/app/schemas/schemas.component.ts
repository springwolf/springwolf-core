import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
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
  selectedSchema: string;
  nameSubscription: Subscription;

  constructor(private asyncApiService: AsyncApiService, private location: Location) {
    this.setSchemaSelectionFromLocation()
  }

  ngOnInit(): void {
    this.location.subscribe(() : void => this.setSchemaSelectionFromLocation())

    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => this.schemas = asyncapi.get(name).components.schemas);
    });
  }

  setSchemaSelection(schema: string): void {
    window.location.hash = '#' + schema
  }
  setSchemaSelectionFromLocation(): void {
    this.selectedSchema = window.location.hash.substr(1);
  }
  resetSchemaSelection(currentSchema: string): void {
    if(this.selectedSchema == currentSchema) {
      this.selectedSchema = undefined
    }
  }
}
