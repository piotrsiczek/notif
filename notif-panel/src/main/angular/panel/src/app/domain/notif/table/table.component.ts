import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../services/notification.service";
import {log} from "util";
import {Notif} from "../notif";

@Component({
  selector: 'notif-table',
  templateUrl: './table.component.html'
})
export class NotifTableComponent implements OnInit {

  private notifList: Notif[];
  private bufferSize: number = 10;
  public testList = ["a", "b", "c"];

  constructor(private notifService: NotificationService) {
    this.notifList=[];
  }

  ngOnInit() {
    this.notifService.getAccelNotif().subscribe(notif => {

      this.notifList.push(notif);
      if (this.notifList.length > this.bufferSize) {
        this.notifList.shift();
      }
      console.log(this.notifList)
    },error => console.log(error));
  }

}
