import { Component, OnInit } from '@angular/core';
import {Model} from '../shared/model.model';
import {ModelsService} from '../services/models.service';

@Component({
  selector: 'app-models',
  templateUrl: './models.component.html',
  providers: [ModelsService]
})
export class ModelsComponent implements OnInit {

  models: { [key: string]: Model };

  constructor(private modelsService: ModelsService) { }

  ngOnInit() {
    this.modelsService.getModels().subscribe(models => this.models = models);
  }

}
