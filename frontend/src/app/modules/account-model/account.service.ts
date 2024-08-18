import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, from, map, Observable } from 'rxjs';
import { Account } from './account';
import { Person } from '../person-model/person';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private url = "http://localhost:8080/account/atm";

  constructor(private http: HttpClient) { }

  getAccount(cardNumber: string, password: string) {
    const body = {
      cardNumber: cardNumber,
      password: password
    };
    return this.http.post<Account>(this.url, body, {
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
    return this.http.put("http://localhost:8080/account/transaction", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
  TransformAmount(fromcard:string,tocard:string,money:number){
    return this.http.put(`http://localhost:8080/account/${fromcard}/${tocard}/${money}`, {
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
    return this.http.put("http://localhost:8080/account/transaction", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });

  }


  GetAccounts(cardNumber: String, amount: number) {
    const body = {
      cardNumber: cardNumber,
      amount: amount,
      method: "Add"
    };
    return this.http.put("http://localhost:8080/account/transaction", body, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  GetAccountBycard(Card: string) : Observable<Account[]> {
    let accounts: Account[] = [];
    return this.http.get<Account>("http://localhost:8080/account/" + Card, {
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
    return this.http.get<any>("http://localhost:8080/account/?email="+email , {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }


  GetAccountByRange(depositFrom: string,depositTo: string) {
    return this.http.get<any>("http://localhost:8080/account/"+depositFrom+"/"+depositTo , {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  DeleteAccountBycard(card: string): Observable<any> {
    const url = `http://localhost:8080/account/${card}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.delete(url, { headers });
  }


  AddAccount(card: any): Observable<any> {
    const url = `http://localhost:8080/account`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.post(url,card, { headers });
  }

  UpdateAccount(card: any): Observable<any> {
    const url = `http://localhost:8080/account`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.put(url,card, { headers });
  }
}

/*= {
    person: {
      user_name: 'mohamed',
      password: '123',
      role: 'Admin',
      email: 'm.shalash0@gmail.com'
    },
    address: {
      state: "behira",
      country: "EG",
      city: "k.h",
      houseID: "777",
      streate: "h8"
    },
    deposit: 2000.0,
    cardNumber: "123",
    phoneNumber: "01095623040",
  };*/