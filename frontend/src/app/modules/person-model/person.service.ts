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

  getPerson(email: string, password: string) {
    return this.http.get<Person>("http://localhost:8080?email="+email+"&pass="+password,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getPersonByEmail(email: string) {
    return this.http.get<Person>("http://localhost:8080?email="+email,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  getPersonByRole(role: string) {
    return this.http.get<Person[]>("http://localhost:8080?role="+role,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});
  }

  addPerson(user: Person) {
    const body = {
      user_name: user.user_name,
      password: user.password,
      email: user.email,
      role: user.role,
    };
      return this.http.post<string>("http://localhost:8080/", body,{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })});

      
  }

    Update(user: Person) {
      const body = {
        user_name: user.user_name,
        password: user.password,
        email: user.email,
        role: user.role,
      };
        return this.http.put<any>("http://localhost:8080/", body,{
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })});
    }

  deletePerson(email: string): Observable<any> {
    const url = `http://localhost:8080?email=${email}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  
    return this.http.delete(url, { headers });
  }
}
