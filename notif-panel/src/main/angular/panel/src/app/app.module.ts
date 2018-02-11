import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {NotifTableComponent} from "./domain/notif/table/table.component";
import {NotificationService} from "./domain/notif/services/notification.service";
import {HttpClientModule} from "@angular/common/http";
import {NotifChartComponent} from "./domain/notif/chart/chart.component";
import {ChartsModule} from "ng2-charts";

@NgModule({
  declarations: [
    AppComponent,
    NotifTableComponent,
    NotifChartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [NotificationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
