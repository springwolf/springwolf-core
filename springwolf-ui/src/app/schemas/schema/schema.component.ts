import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-schema',
  template: '<pre><code [highlight]="json"></code></pre>',
  styles: [`code {
    margin-top: 16px;
    padding: 8px;
    border-radius: 4px;
  }`]
})
export class SchemaComponent implements OnInit {

  @Input() root: string;
  @Input() schema: any;
  json: string;

  constructor() { }

  ngOnInit(): void {
    this.json = JSON.stringify(this.schema[this.root], null, 2);
  }

}
