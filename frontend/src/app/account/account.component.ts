import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../modules/account-model/account.service';
import { Account } from '../modules/account-model/account';
import { NgFor, NgIf } from '@angular/common';
import { Person } from '../modules/person-model/person';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [NgFor,NgIf],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {
  accounts?:Array<any>= [];
  dummy?:any;
  search(){
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const card = document.getElementById("card") as HTMLInputElement;
    const deposit1 = document.getElementById("deposit") as HTMLInputElement;
    const deposit2 = document.getElementById("deposit2") as HTMLInputElement;
    if (element.value === "card") {
      this.accountService.GetAccountBycard(card.value).subscribe({
        next: (v) => {          
          this.dummy =v;
          //this.accounts?.push(v);
        },
        error: (e) => {console.error(e)},
        complete:()=>{
          this.accounts?.push(this.dummy);
        }
      });
    }else if(element.value === "deposit"){
      this.accountService.GetAccountBycard(card.value).subscribe({
        next: (v) => {
          this.accounts=[v];
        },
        error: (e) => {console.error(e)},
        complete:()=>{
        }
      });
    }
  }

  Back(){
    this.router.navigate(["/process"]);
  }

  Add(){
    localStorage.setItem('Mission', "Add");
    this.router.navigate(["/add-acount"]);
  }

  Update(account:any){
    localStorage.setItem('Mission', "Update");
    localStorage.setItem('account', JSON.stringify(account));
    this.router.navigate(["/add-acount"]);
  }

  Delete(card: string) {
    this.accountService.DeleteAccountBycard(card).subscribe({
      next: (response) => console.log('Delete successful:', response),
      error: (error) => console.error('Delete failed:', error),
      complete: () => window.location.reload()
    });
  }

  logSelectedItem(): void {
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const card = document.getElementById("card") as HTMLInputElement;
    const deposit1 = document.getElementById("deposit") as HTMLInputElement;
    const deposit2 = document.getElementById("deposit2") as HTMLInputElement;
    if (element.value === "card") {
      card.classList.remove("hidden2");
      deposit1.classList.add("hidden");
      deposit2.classList.add("hidden");
    }else if(element.value === "deposit"){
      card.classList.add("hidden2");
      deposit1.classList.remove("hidden");
      deposit2.classList.remove("hidden");
    }else {
      card.classList.add("hidden2");
      deposit1.classList.add("hidden");
      deposit2.classList.add("hidden");
    }

  }

  user:Person;
  constructor(private router: Router, private accountService:AccountService){
    const data =localStorage.getItem('data');
    this.user = data? JSON.parse(data):null;
  }

}

