import { Component, OnInit } from '@angular/core';
import { Info } from '../shared/models/info.model';
import { AsyncApiService } from '../shared/asyncapi.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  info: Info;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.info = this.asyncApiService.getAsyncApi().info;
  }

}
