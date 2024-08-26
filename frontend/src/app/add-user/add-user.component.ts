import { Component,OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from '../modules/person-model/person.service';
import { NgIf } from '@angular/common';
import { Person } from '../modules/person-model/person';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [NgIf,FormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {

  constructor(private router:Router,private userService:PersonService){
    this.isAdd = localStorage.getItem('Mission') ==='Add';  
    
    const logger =localStorage.getItem('data');
    this.person = logger? JSON.parse(logger):null;

    const data =localStorage.getItem('user');
    this.user = data? JSON.parse(data):{role:'User',email:"",password:"",user_name:""};

  }
  
  isAdd?:boolean =false ;
  person :Person;
  user :Person;


  Back(){
    this.router.navigate(['/person']);
  }
  Add(){
    
    this.userService.addPerson(this.user).subscribe({
      next: (v) => {  },
      error: (e) => { alert("Error in Adding Email May Be Exist Or Try Again Later"); },
      complete:()=>{}
    });
    this.router.navigate(['/person'])
  }

  Update(){
    this.userService.Update(this.user).subscribe({
      next: (v) => {  },
      error: (e) => { console.error(e) },
      complete:()=>{}
    });
    this.router.navigate(['/person'])
  }
}
