import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LogService } from '../modules/logs/log.service';
import { Log } from '../modules/logs/log';
import { CommonModule, NgFor } from '@angular/common';
import { Observable } from 'rxjs';
import { Person } from '../modules/person-model/person';

@Component({
  selector: 'app-logs',
  standalone: true,
  imports: [NgFor,CommonModule],
  templateUrl: './logs.component.html',
  styleUrl: './logs.component.css'
})
export class LogsComponent {
  logSelectedItem(): void {
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const searchInput = document.getElementById("searchInput") as HTMLSelectElement;
    const element2 = document.getElementById("time") as HTMLInputElement;
    const element3 = document.getElementById("time2") as HTMLInputElement;
    const date = document.getElementById("date") as HTMLInputElement;
    if (element.value === "Time") {
      searchInput.classList.add("hidden3");
      element2.classList.remove("hidden");
      element3.classList.remove("hidden");
      date.classList.remove("hidden2");
    } else if(element.value === "date"){
      searchInput.classList.add("hidden3");
      element2.classList.add("hidden");
      element3.classList.add("hidden");
      date.classList.remove("hidden2");
    }else if(element.value === "role"){
      searchInput.classList.remove("hidden3");
      element2.classList.add("hidden");
      element3.classList.add("hidden");
      date.classList.add("hidden2");
    }else{
      searchInput.classList.add("hidden3");
      element2.classList.add("hidden");
      element3.classList.add("hidden");
      date.classList.add("hidden2");
    }

  }

  search() {
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const text1 = document.getElementById("searchInput") as HTMLInputElement;
    const time1 = document.getElementById("time") as HTMLInputElement;
    const time2 = document.getElementById("time2") as HTMLInputElement;
    const date = document.getElementById("date") as HTMLInputElement;
    if (element.value === "Time") {
      if(time1.value >= time2.value) alert("Time 1 cant be bigger than time 1 or equal");
      else{
        this.logs=this.logservice.getlogBydateAndTime(date.value,time1.value,time2.value);
      }
    } else if(element.value === "date") {
      this.logs=this.logservice.getlogBydate(date.value);
    } else{
      this.logs=this.logservice.getlogBykind(text1.value);
    }
  }

  Back() {
    this.router.navigate(["/process"]);
  }
time?:string;
date?:string;
logs?: Observable<Log[]>;
user?: Person;
constructor(private router: Router, private logservice:LogService){
  const data = localStorage.getItem('data');
    this.user = data ? JSON.parse(data) : null;
    if (this.user)
    this.logs = this.logservice.getlogByEmail(this.user.email);
}
}
