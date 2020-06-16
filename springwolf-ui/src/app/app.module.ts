import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { HeaderComponent } from './header/header.component';
import { InfoComponent } from './info/info.component';
import { AsyncApiService } from './shared/asyncapi.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    InfoComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule
  ],
  providers: [AsyncApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
