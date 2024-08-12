import { Component } from '@angular/core';
import { Person } from '../modules/person-model/person';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';
import { PersonService } from '../modules/person-model/person.service';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [NgFor],
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

  person?: Array<Person>;

  constructor(private router: Router, private userService: PersonService) {


    const data = localStorage.getItem('RoleSearch');
    if ("Maneger" === data) {
      userService.getPersonByRole("Maneger").subscribe({
        next: (v) => {
          this.person = v;
        },
        error: (e) => { console.error(e) },
        complete: () => {
        }
      });
    } else {
      userService.getPersonByRole("User").subscribe({
        next: (v) => {
          this.person = v;
        },
        error: (e) => { console.error(e) },
        complete: () => {
        }
      });
    }

  }
}
