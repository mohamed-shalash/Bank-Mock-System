import { Injectable } from '@angular/core';
import { Log } from './log';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private http:HttpClient) { }


  getlogBykind(kind: string) {
    return this.http.get<Log[]>("http://localhost:8080/log/"+kind,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getlogBydate(date: string) {
    return this.http.get<Log[]>("http://localhost:8080/log?date="+date,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getlogBydateAndTime(date: string,timefrom:string,timeto:string) {
    return this.http.get<Log[]>("http://localhost:8080/log?date="+date+"&timeFrom="+timefrom+"&timeTo="+timeto,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }
}
