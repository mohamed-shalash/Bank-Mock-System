import { Component } from '@angular/core';
import { AccountService } from '../../modules/account-model/account.service';
import { Router } from '@angular/router';
import { Account } from '../../modules/account-model/account';

@Component({
  selector: 'app-balance',
  standalone: true,
  imports: [],
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.css'
})
export class BalanceComponent {

  back(){
    window.location.href = "atm";
  }

  account: Account;


  constructor(private router:Router,private accountService :AccountService){
    const data =localStorage.getItem('data');
    this.account = data? JSON.parse(data):null;
  }

  ngOnInit(): void {

    this.accountService.getAccount(this.account.cardNumber, this.account.password).subscribe({
      next: (v) => {
        this.account =v;
      },
      error: (e) => {console.error(e)},
      complete: () => {
              localStorage.setItem('data', JSON.stringify(this.account));
      }
      });
  }
}
