import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Schema } from '../shared/models/schema.model';

@Component({
  selector: 'app-schemas',
  templateUrl: './schemas.component.html',
  styleUrls: ['./schemas.component.css']
})
export class SchemasComponent implements OnInit {
  
  schemas: Map<String, Schema>;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.schemas = this.asyncApiService.getAsyncApi().schemas;
  }

}
