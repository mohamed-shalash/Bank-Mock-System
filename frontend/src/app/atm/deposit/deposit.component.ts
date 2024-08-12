import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Account } from '../../modules/account-model/account';
import { AccountService } from '../../modules/account-model/account.service';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './deposit.component.html',
  styleUrl: './deposit.component.css'
})
export class DepositComponent {

  Back(){
    //window.location.href="atm"
    this.router.navigate(["/atm"])
  }

  account:Account ;
  money?:number;

  constructor(private router: Router,private accountService :AccountService) {
    const data = localStorage.getItem('data');
    this.account = data ? JSON.parse(data) : null;
  }


  depositMoney(){
    if(this.money  ){
          /*this.account.deposit =this.account.deposit-this.money;
          localStorage.setItem('data',JSON.stringify(this.account));*/
          this.accountService.AddPalance(this.account.cardNumber,this.money).subscribe(
            {
            next:res=>{
              alert("You Added Amount "+this.money+" $ From "+this.account.cardNumber)
            },
            error:error=>console.error(error),
            complete:()=>{
              this.router.navigate(['/atm'])
            }
          }
    );
      
      
    }else{
      alert("cant draw amount")
    }
  }
}
