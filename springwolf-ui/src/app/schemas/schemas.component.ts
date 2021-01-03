import { Component, OnInit } from '@angular/core';
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

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(
      asyncapi => this.schemas = asyncapi.components.schemas
    );
  }

}
