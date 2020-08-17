import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-schema',
  template: '<pre><code [highlight]="schemaProperties"></code></pre>',
  styles: [`code {
    margin-top: 16px;
    padding: 8px;
    border-radius: 4px;
  }`]
})
export class SchemaComponent implements OnInit {

  @Input() schema: any;
  schemaProperties: string;

  constructor() { }

  ngOnInit(): void {
    this.schemaProperties = JSON.stringify(this.schema.properties, null, 2);
  }

}
