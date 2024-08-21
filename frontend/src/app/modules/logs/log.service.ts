import { Injectable } from '@angular/core';
import { Log } from './log';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LogService {

  constructor(private http:HttpClient) { }

url="http://localhost:8080/logs"

  getlogBykind(kind: string,page:number,size:number) {
    return this.http.get<Log[]>(this.url+"/"+kind+`?page=${page}&size=${size}`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getlogBydate(date: string,page:number,size:number) {
    return this.http.get<Log[]>(this.url+"?date="+date+`&page=${page}&size=${size}`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getlogBydateAndTime(date: string,timefrom:string,timeto:string,page:number,size:number) {
    return this.http.get<Log[]>(this.url+"?date="+date+"&timeFrom="+timefrom+"&timeTo="+timeto+`&page=${page}&size=${size}`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getlogByEmail(email: string) {
    return this.http.get<Log[]>(this.url+"?email="+email,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }
}
