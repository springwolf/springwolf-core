import { Component, Input } from '@angular/core';
import { Schema } from 'src/app/shared/models/schema.model';

@Component({
  selector: 'app-schema',
  templateUrl: './schema.component.html',
  styleUrls: ['./schema.component.css']
})
export class SchemaComponent {

  @Input() schema: Schema;

  public getSchemaUrl(fragment: string): string {
    let baseUrl = window.location.href;
    const lastHashPosition = baseUrl.lastIndexOf('#');
    if(lastHashPosition >= 0) {
      baseUrl = baseUrl.substring(0, lastHashPosition);
    }
    return baseUrl + '#' + fragment;
  }

}
