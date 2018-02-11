import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import * as EventSource from 'eventsource';
import {Observable} from "rxjs/Observable";
import {Notif} from "../notif";

@Injectable()
export class NotificationService {

  constructor() { }

  public getAccelNotif(): Observable<Notif> {
    return new Observable<Notif>(obs => {
      const es = new EventSource('/notifications');
      es.addEventListener('message', (evt) => {
        let notif: Notif = JSON.parse(evt.data);
        obs.next(notif);
      });
      return () => es.close();
    });
  }



}
