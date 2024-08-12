import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../../modules/account-model/account.service';
import { FormsModule } from '@angular/forms';
import { Person } from '../../modules/person-model/person';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-add-account',
  standalone: true,
  imports: [FormsModule,NgIf],
  templateUrl: './add-account.component.html',
  styleUrl: './add-account.component.css'
})
export class AddAccountComponent {
  isAdd?:boolean =false ;
  account :any;
  person :Person;
  constructor(private router:Router,private accountService:AccountService){
    this.isAdd = localStorage.getItem('Mission') ==='Add';  

    const logger =localStorage.getItem('data');
    this.person = logger? JSON.parse(logger):null;

    const data =localStorage.getItem('account');
    this.account = data? JSON.parse(data):
    {
      user: {
        user_name: '',
        password: '',
        role: '',
        email: ''
      },
      address: {
        account: null,
        state: "",
        country: "EG",
        city: "",
        houseID: "",
        streate: ""
      },
      deposit: 0,
      cardNumber: "",
      phoneNumber: "",
      password: ""
    };
  }
  Add(){
    
    this.accountService.AddAccount(this.account).subscribe({
      next: (v) => {  },
      error: (e) => { console.error(e) },
      complete:()=>{}
    });
    this.router.navigate(['/account'])
  }

  Update(){
    this.accountService.UpdateAccount(this.account).subscribe({
      next: (v) => {  },
      error: (e) => { console.error(e) },
      complete:()=>{}
    });
    localStorage.setItem('account',"")
    this.router.navigate(['/account'])
  }
}
