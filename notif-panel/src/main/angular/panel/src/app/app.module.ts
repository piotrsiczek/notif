import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {NotifTableComponent} from "./domain/notif/table/table.component";
import {NotificationService} from "./domain/notif/services/notification.service";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    NotifTableComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
