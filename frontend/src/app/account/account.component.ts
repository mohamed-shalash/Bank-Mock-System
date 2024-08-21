import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../modules/account-model/account.service';
import { Account } from '../modules/account-model/account';
import { NgFor, NgIf } from '@angular/common';
import { Person } from '../modules/person-model/person';
import { Observable, Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-account',
  standalone: true,
  imports: [NgFor, NgIf,CommonModule],
  templateUrl: './account.component.html',
  styleUrl: './account.component.css'
})
export class AccountComponent {
  private subscription: Subscription = new Subscription();
  accounts?: Array<any> = [];
  dummy?: any;
  page =0;
  size=2;
  show=false;
  search() {
    this.show=true;
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const card = document.getElementById("card") as HTMLInputElement;
    const deposit1 = document.getElementById("deposit") as HTMLInputElement;
    const deposit2 = document.getElementById("deposit2") as HTMLInputElement;
    if (element.value === "card") {
      this.observer$ = this.accountService.GetAccountBycard(card.value)
    } else if (element.value === "deposit") {
      this.observer$=this.accountService.GetAccountByRange(Number(deposit1.value), Number(deposit2.value),this.page,this.size)
    }
  }

  next(){
    this.page+=1;
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const card = document.getElementById("card") as HTMLInputElement;
    const deposit1 = document.getElementById("deposit") as HTMLInputElement;
    const deposit2 = document.getElementById("deposit2") as HTMLInputElement;
    if (element.value === "card") {
      this.observer$ = this.accountService.GetAccountBycard(card.value)
    } else if (element.value === "deposit") {
      this.observer$=this.accountService.GetAccountByRange(Number(deposit1.value), Number(deposit2.value),this.page,this.size)
    }
    if(this.observer$)
      this.observer$.subscribe((data: any[]) => {
        if (data.length === 0) {
          this.page = -1;
          this.next();
        }
      });
  }

  brevious(){
    this.page-=1;
    if(this.page<0)this.page=0;
    const element = document.getElementById("mySelect") as HTMLSelectElement;
    const card = document.getElementById("card") as HTMLInputElement;
    const deposit1 = document.getElementById("deposit") as HTMLInputElement;
    const deposit2 = document.getElementById("deposit2") as HTMLInputElement;
    if (element.value === "card") {
      this.observer$ = this.accountService.GetAccountBycard(card.value)
    } else if (element.value === "deposit") {
      this.observer$=this.accountService.GetAccountByRange(Number(deposit1.value), Number(deposit2.value),this.page,this.size)
    }
  }

  Back() {
    this.router.navigate(["/process"]);
  }

  Add() {
    localStorage.setItem('Mission', "Add");
    this.router.navigate(["/add-acount"]);
  }

  Update(account: any) {
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
    } else if (element.value === "deposit") {
      card.classList.add("hidden2");
      deposit1.classList.remove("hidden");
      deposit2.classList.remove("hidden");
    } else {
      card.classList.add("hidden2");
      deposit1.classList.add("hidden");
      deposit2.classList.add("hidden");
    }

  }

  user?: Person;

  ngOnDestroy() {
    this.subscription.unsubscribe(); 
  }
  constructor(private router: Router, private accountService: AccountService) {
    const data = localStorage.getItem('data');
    this.user = data ? JSON.parse(data) : null;
    if (this.user)
    this.observer$ = this.accountService.GetAccountForUser(this.user.email);

  }
  observer$?:Observable<Account[]>;


}

