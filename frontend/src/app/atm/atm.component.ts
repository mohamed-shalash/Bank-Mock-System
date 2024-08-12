import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AccountService } from '../modules/account-model/account.service';
import { Router } from '@angular/router';
import { Account } from '../modules/account-model/account';
import { of } from 'rxjs';

@Component({
  selector: 'app-atm',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './atm.component.html',
  styleUrl: './atm.component.css'
})
export class ATMComponent {


  account?: Account ;

  cardnumber?:string;
  password?:string;
  config:string | undefined;
  signcard() {
    if(this.cardnumber && this.password)
      this.accountservice.getAccount(this.cardnumber, this.password).subscribe({
        next: (v) => {
          this.account =v;
          //alert(v.deposit);
        },
        error: (e) => {console.error(e)},
        complete: () => {
          //alert(this.account?.deposit);
                
                localStorage.setItem('data', JSON.stringify(this.account));
                this.router.navigate(['/atm']);
        }
        });
          
      
      else
      alert(this.cardnumber+"          "+this.password)

    
      /*1.this.accountservice.setData(this.account);
      this.router.navigate(['/atm']);*/

      //window.location.href = 'atm';

      
      //localStorage.setItem('data', JSON.stringify(this.account));
     // this.router.navigate(['/atm']);

     //3. this.router.navigate(['/atm'], { queryParams: { data: JSON.stringify(this.account) } });

  }

  constructor(private router: Router,private accountservice : AccountService) { }
}


/*= {
    person: {
      user_name: 'mohamed',
      password: '123',
      role: 'Admin',
      email: 'm.shalash0@gmail.com'
    },
    address: {
      state: "behira",
      country: "EG",
      city: "k.h",
      houseID: "777",
      streate: "h8"
    },
    deposit: 2000.0,
    cardNumber: "123",
    phoneNumber: "01095623040",
  };*/