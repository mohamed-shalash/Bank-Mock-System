import { Injectable } from '@angular/core';
import { Person } from './person';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private user?: Person;

  constructor(private http: HttpClient) {}
  Url ="http://localhost:8080"
  getPerson(email: string, password: string) {
    return this.http.get<Person>(this.Url+"?email="+email+"&pass="+password,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getPersonByEmail(email: string) {
    return this.http.get<Person>(this.Url+"?email="+email,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getPersonByRole(role: string,page:number,size:number) {
    return this.http.get<Person[]>(this.Url+"?role="+role+`&page=${page}&size=${size}`,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  addPerson(user: Person) {
    const body = {
      "user_name": user.user_name,
      "password": user.password,
      "email": user.email,
      "role": user.role,
    };
      return this.http.post<any>(this.Url+"/", body,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});

      
  }

    Update(user: Person) {
      const body = {
        "user_name": user.user_name,
        "password": user.password,
        "email": user.email,
        "role": user.role,
      };
        return this.http.put<any>(this.Url, body,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })});
    }

  deletePerson(email: string): Observable<any> {
    const url = this.Url+`?email=${email}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.delete(url, { headers });
  }
}
