import { Component, OnInit } from '@angular/core';
import { AsyncApi } from '../shared/models/asyncapi.model';
import { Info } from '../shared/models/info.model';
import { AsyncApiService } from '../shared/asyncapi.service';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  asyncApiData: AsyncApi;
  info: Info;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.asyncApiService.getAsyncApi().subscribe(asyncapi => {
      this.asyncApiData = asyncapi;
      this.info = asyncapi.info;
    });
  }

  download(): Boolean {
    var json = JSON.stringify(this.asyncApiData.info.asyncApiJson, null, 2);
    var bytes = new TextEncoder().encode(json);
    var blob = new Blob([bytes], { type: 'application/json' });
    var url = window.URL.createObjectURL(blob);
    window.open(url);

    return false;
  }

}
