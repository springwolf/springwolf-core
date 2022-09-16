import { Component, OnInit } from '@angular/core';
import { AsyncApi } from '../shared/models/asyncapi.model';
import { Info } from '../shared/models/info.model';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  asyncApiData: AsyncApi;
  info: Info;
  nameSubscription: Subscription;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => {
        this.asyncApiData = asyncapi.get(name);
        this.info = asyncapi.get(name).info;
      });
    });
  }

  async download(): Promise<Boolean> {
    var json = JSON.stringify(this.asyncApiData, null, 2);
    var bytes = new TextEncoder().encode(json);
    var blob = new Blob([bytes], { type: 'application/json' });
    var url = window.URL.createObjectURL(blob);
    window.open(url);

    return false;
  }

}
