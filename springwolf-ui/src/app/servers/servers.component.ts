import { Component, OnInit } from '@angular/core';
import { forkJoin, Subscription, zip } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Server } from '../shared/models/server.model';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {

  servers: Map<String, Server>;
  nameSubscription: Subscription;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.nameSubscription = this.asyncApiService.getCurrentAsyncApiName().subscribe(name => {
      this.asyncApiService.getAsyncApis().subscribe(asyncapi => this.servers = asyncapi.get(name).servers );
    });
  }

}
