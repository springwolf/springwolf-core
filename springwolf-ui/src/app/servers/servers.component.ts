import { Component, OnInit } from '@angular/core';
import { AsyncApiService } from '../shared/asyncapi.service';
import { Server } from '../shared/models/server.model';

@Component({
  selector: 'app-servers',
  templateUrl: './servers.component.html',
  styleUrls: ['./servers.component.css']
})
export class ServersComponent implements OnInit {

  servers: Map<String, Server>;

  constructor(private asyncApiService: AsyncApiService) { }

  ngOnInit(): void {
    this.servers = this.asyncApiService.getAsyncApi().servers;
  }

}
