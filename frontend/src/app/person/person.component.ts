import { Component } from '@angular/core';
import { Person } from '../modules/person-model/person';
import { Router } from '@angular/router';
import { CommonModule, NgFor } from '@angular/common';
import { PersonService } from '../modules/person-model/person.service';
import {  merge, Observable, scan } from 'rxjs';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [NgFor,CommonModule],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent {

  Add() {
    localStorage.setItem('Mission', "Add");
    localStorage.setItem('user', "");
    this.router.navigate(['/add-user']);
  }
  Back() {
    this.router.navigate(['/process']);
  }

  User?: Person;
  Update(Email: string) {

    this.userService.getPersonByEmail(Email).subscribe({
      next: (v) => { this.User = v; },
      error: (e) => { console.error(e) },
      complete: () => {
        localStorage.setItem('Mission', "Update");
        localStorage.setItem('user', JSON.stringify(this.User));
        this.router.navigate(['/add-user']);
      }
    });

  }

  Delete(Email: string) {
    this.userService.deletePerson(Email).subscribe({
      next: (response) => console.log('Delete successful:', response),
      error: (error) => console.error('Delete failed:', error),
      complete: () => window.location.reload()
    });
  }

  persons$?:Observable<Person[]>;
  constructor(private router: Router, private userService: PersonService) {


    const data = localStorage.getItem('RoleSearch');
    
    if ("Admin" === data) {
      this.persons$ =merge( this.userService.getPersonByRole('Admin'),this.userService.getPersonByRole('Maneger')).pipe(
        scan((acc: Person[], val: Person[]) => [...acc, ...val], []) 
      );
      this.persons$.subscribe(val => console.log(val));
    /*}
    else if ("Maneger" === data) {
      this.persons$=userService.getPersonByRole("Maneger");*/
    } else {
      this.persons$=userService.getPersonByRole("User");;
    }

  }
}
