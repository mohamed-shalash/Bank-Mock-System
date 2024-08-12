import { Router } from '@angular/router';
import { AccountService } from '../../modules/account-model/account.service';
import { FormsModule } from '@angular/forms';
import { Account } from '../../modules/account-model/account';
import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-draw',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './draw.component.html',
  styleUrl: './draw.component.css'
})
export class DrawComponent {
  constructor(private router:Router,private accountService :AccountService){
    const data =localStorage.getItem('data');
    this.account = data? JSON.parse(data):null;
  }

  ngOnInit(): void {
    /*const data =localStorage.getItem('data');
    this.account = data? JSON.parse(data):null;*/

    this.accountService.getAccount(this.account.cardNumber, this.account.password).subscribe({
      next: (v) => {
        this.account =v;
        //alert(v.deposit);
      },
      error: (e) => {console.error(e)},
      complete: () => {
       // alert(this.account?.deposit);
              
              localStorage.setItem('data', JSON.stringify(this.account));
      }
      });
  }

  withdrawMoney(){
    if(this.money  ){
      if( this.money<=0 || this.money> this.account.deposit)  alert("cant draw amount")
      else{
          /*this.account.deposit =this.account.deposit-this.money;
          localStorage.setItem('data',JSON.stringify(this.account));*/
          this.accountService.DrawPalance(this.account.cardNumber,this.money).subscribe({
            next:res=>{
              alert("You Draw Amount "+this.money+" $ From "+this.account.cardNumber)
            },
            error:error=>console.error(error),
            complete:()=>{
              this.router.navigate(['/atm'])
            }
          });
      }
      
    }else{
      alert("cant draw amount")
    }
  }
  Back(){
    this.router.navigate(['/atm']);
  }

  account:Account ;
  money?:number;
}
