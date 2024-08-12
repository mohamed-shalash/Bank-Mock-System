import { Component } from '@angular/core';
import { Person } from '../../modules/person-model/person';
import { PersonService } from '../../modules/person-model/person.service';
import { AccountService } from '../../modules/account-model/account.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from '../../modules/account-model/account';

@Component({
  selector: 'app-main',
  standalone: true,
  imports: [],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {
  navigateTo(page:string){
    if(page === "atm-login") 
      {
          localStorage.setItem('data',"");
      }
    this.router.navigate(['/'+page]);
  }



  //object: Account;

  /*constructor(private router: Router,private accountService: AccountService) {
    this.object = this.accountService.getData();
    //alert(this.object.person.user_name);
  }*/

    constructor(private router: Router) {
      /*const data = localStorage.getItem('data');
      this.object = data ? JSON.parse(data) : null;*/
    }

    /*constructor(private route: ActivatedRoute) {
        this.route.queryParams.subscribe(params => {
          this.object = JSON.parse(params['data']);
        });
        alert(this.object?.person.email)
    }*/


  
}
