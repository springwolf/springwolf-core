import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HeaderComponent } from './header/header.component';
import { InfoComponent } from './info/info.component';
import { AsyncApiService } from './shared/asyncapi.service';
import { ServersComponent } from './servers/servers.component';
import { ChannelsComponent } from './channels/channels.component';
import { ChannelMainComponent } from './channels/channel-main/channel-main.component';
import { SchemasComponent } from './schemas/schemas.component';
import { SchemaComponent } from './schemas/schema/schema.component';
import { HighlightModule, HIGHLIGHT_OPTIONS } from 'ngx-highlightjs';

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
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    HighlightModule
  ],
  providers: [
    AsyncApiService,
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
