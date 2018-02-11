import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../services/notification.service";
import {log} from "util";
import {Notif} from "../notif";

@Component({
  selector: 'notif-table',
  templateUrl: './table.component.html',
})
export class NotifTableComponent implements OnInit {

  private notifList: Notif[];
  private bufferSize: number = 10;

  constructor(private notifService: NotificationService) {
    this.notifList=[];
    let notif = new Notif();
    notif.x = 12332;
    notif.y = 223123;
    notif.z = 234541;
    notif.time = "10.02.2018 12:25:34.432"
    this.notifList.push(notif);
    this.notifList.push(notif);
    this.notifList.push(notif);
    this.notifList.push(notif);
    this.notifList.push(notif);
    this.notifList.push(notif);
  }

  ngOnInit() {
    this.notifService.getAccelNotif().subscribe(notif => {
      this.notifList.push(notif);
      if (this.notifList.length > this.bufferSize) {
        this.notifList.shift();
      }
    },error => console.log(error));
  }

}
