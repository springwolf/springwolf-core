import { Component, OnInit } from '@angular/core';
import { Info } from '../shared/models/info.model';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.css']
})
export class InfoComponent implements OnInit {

  info: Info;
  nameSubscription: Subscription;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => this.info = asyncapi.get(name).info);
    });
  }

}
