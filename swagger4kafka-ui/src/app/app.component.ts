import {Component, OnInit} from '@angular/core';
import {InfoService} from './services/info.service';
import {Info} from './shared/info';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [InfoService]
})
export class AppComponent implements OnInit {
  info: Info;

  constructor(private infoService: InfoService) {}

  ngOnInit(): void {
    this.infoService
      .getInfo()
      .subscribe(info => this.info = info);
  }

}
