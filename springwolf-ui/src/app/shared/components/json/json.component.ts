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
  @Input() json: string;

  ngOnInit(): void {
    this.json = this.json === undefined ? JSON.stringify(this.data, null, 2) : this.json;
  }

}
