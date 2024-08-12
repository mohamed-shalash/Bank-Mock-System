import { Component,OnInit } from '@angular/core';
import { Person } from '../modules/person-model/person';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-intermediate',
  standalone: true,
  imports: [NgIf],
  templateUrl: './intermediate.component.html',
  styleUrl: './intermediate.component.css'
})
export class IntermediateComponent {

  constructor(private router: Router){
    const data =localStorage.getItem('data');
    this.User = data? JSON.parse(data):null;

  }

  ngOnInit() {
    this.isAdmin = this.User.role == 'Admin';    
  }
  isAdmin?:boolean;
  User:Person;
  navigateTo(page:string){
    if(page =="personManeger"){
      localStorage.setItem('RoleSearch', "Maneger");
      this.router.navigate(["/person"]);
      
    }
    else if(page =="personUser"){
      localStorage.setItem('RoleSearch', "User");
      this.router.navigate(["/person"]);
    }else{
    this.router.navigate(["/"+page]);
    }
  }
}
