import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../../modules/account-model/account.service';
import { FormsModule } from '@angular/forms';
import { Account } from '../../modules/account-model/account';

@Component({
  selector: 'app-transformation',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './transformation.component.html',
  styleUrl: './transformation.component.css'
})
export class TransformationComponent {


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

  TransformMoney(){
    if(this.money  ){
      if( this.money<=0 || this.money> this.account.deposit || !this.card )  alert("cant tranform amount")
      else{
          this.accountService.TransformAmount(this.account.cardNumber,this.card,this.money).subscribe({
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
  card?:string;
}
