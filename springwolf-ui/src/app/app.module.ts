import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { HighlightModule, HIGHLIGHT_OPTIONS } from 'ngx-highlightjs';
import { environment } from './../environments/environment';
import { AppComponent } from './app.component';
import { ChannelMainComponent } from './channels/channel-main/channel-main.component';
import { ChannelsComponent } from './channels/channels.component';
import { HeaderComponent } from './header/header.component';
import { InfoComponent } from './info/info.component';
import { MaterialModule } from './material.module';
import { SchemaComponent } from './schemas/schema/schema.component';
import { SchemasComponent } from './schemas/schemas.component';
import { ServersComponent } from './servers/servers.component';
import { AsyncApiService } from './shared/asyncapi.service';
import { MockServer } from './shared/mock/mock-server';
import { PublisherService } from './shared/publisher.service';
import { FormsModule } from '@angular/forms';
import { JsonComponent } from './shared/components/json/json.component';
import {AsyncApiMapperService} from "./shared/asyncapi-mapper.service";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    InfoComponent,
    ServersComponent,
    ChannelsComponent,
    ChannelMainComponent,
    SchemasComponent,
    SchemaComponent,
    JsonComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    HighlightModule,
    HttpClientModule,
    FormsModule,
    environment.production ? [] : HttpClientInMemoryWebApiModule.forRoot(MockServer, { delay: 100 })
  ],
  providers: [
    AsyncApiService,
    AsyncApiMapperService,
    PublisherService,
    {
      provide: HIGHLIGHT_OPTIONS,
      useValue: {
        languages: function () {
          return { typescript: () => import('highlight.js/lib/languages/typescript') };
        }
      }
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
