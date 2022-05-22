import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-json',
  template: '<pre><code [highlight]="json"></code></pre>',
  styles: [`code {
    margin-top: 16px;
    padding: 8px;
    border-radius: 4px;
  }`]
})
export class JsonComponent implements OnInit {

  @Input() data: any;
  json: string;

  ngOnInit(): void {
    this.json = JSON.stringify(this.data, null, 2);
  }

}
