import {Component, Input, OnInit} from '@angular/core';
import {Model} from '../../shared/model.model';

@Component({
  selector: 'app-model',
  templateUrl: './model.component.html',
})
export class ModelComponent implements OnInit {
  @Input() modelName: string;
  @Input() model: Model;
  @Input() isOpen = true;

  constructor() { }

  ngOnInit() {
  }

}
