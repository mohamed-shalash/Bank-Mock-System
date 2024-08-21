import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, from, map, Observable } from 'rxjs';
import { Account } from './account';
import { Person } from '../person-model/person';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private url = "http://localhost:8080/accounts";

  constructor(private http: HttpClient) { }

  getAccount(cardNumber: string, password: string) {
    const body = {
      cardNumber: cardNumber,
      password: password
    };
    return this.http.post<Account>(this.url+"/atm", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  DrawPalance(cardNumber: String, amount: number) {
    const body = {
      cardNumber: cardNumber,
      amount: amount,
      method: "Draw"
    };
    return this.http.put(this.url+"/transactions", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }


  TransformAmount(fromcard:string,tocard:string,money:number){
    const body = {
      "from": fromcard,
      "to": tocard,
      "amount": money+0.0
    };
    return this.http.put(`${this.url}/transfer`,body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  AddPalance(cardNumber: String, amount: number) {
    const body = {
      cardNumber: cardNumber,
      amount: amount,
      method: "Add"
    };
    return this.http.put(this.url+"/transactions", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });

  }




  GetAccountBycard(Card: string) : Observable<Account[]> {
    let accounts: Account[] = [];
    return this.http.get<Account>(this.url+"/" + Card, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    }).pipe(
      map(account => {
        accounts.push(account);
        return accounts;
      })
    );;
  }

  GetAccountForUser(email :string) :Observable<Account[]>{
    return this.http.get<any>(this.url+"/?email="+email , {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }


  GetAccountByRange(depositFrom: number,depositTo: number,page:number,size:number) {
    const body = {
      "from": depositFrom+0.00001,
      "to": depositTo+0.0
    };
    return this.http.post<any>(this.url+"/" +`?page=${page}&size=${size}`,body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  DeleteAccountBycard(card: string): Observable<any> {
    const url = `${this.url}/${card}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.delete(url, { headers });
  }


  AddAccount(card: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.post(this.url,card, { headers });
  }

  UpdateAccount(card: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.put(this.url,card, { headers });
  }
}