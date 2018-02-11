import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../services/notification.service";
import {log} from "util";
import {Notif} from "../notif";

@Component({
  selector: 'notif-chart',
  templateUrl: './chart.component.html',
})
export class NotifChartComponent implements OnInit {

  private notifList: Notif[];
  private bufferSize: number = 10;


  // lineChart
  public lineChartData:Array<any> = [
    [],
    []
  ];
  public lineChartLabels:Array<any> = [];
  public lineChartType:string = 'line';

  constructor(private notifService: NotificationService) {
    // this.notifList=[];
    // let notif = new Notif();
    // notif.x = 12332;
    // notif.y = 223123;
    // notif.z = 234541;
    // notif.time = "10.02.2018 12:25:34.432"
    // this.notifList.push(notif);
    // this.notifList.push(notif);
    // this.notifList.push(notif);
    // this.notifList.push(notif);
    // this.notifList.push(notif);
    // this.notifList.push(notif);
    //




  }

  ngOnInit() {
    this.notifService.getAccelNotif().subscribe(notif => {
      this.updateChart(notif);
    },error => console.log(error));
  }

  private updateChart(notif: Notif) {
    console.log(notif.x);

    this.lineChartData[0].push(notif.x);
    this.lineChartData[1].push(notif.y);
    this.lineChartLabels.push(notif.time);

    if (this.lineChartLabels.length > this.bufferSize) {
      this.lineChartData[0].shift();
      this.lineChartData[1].shift();
      this.lineChartLabels.shift();
    }
  }

}
