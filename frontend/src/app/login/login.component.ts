import { Component } from '@angular/core';
import { Person } from '../modules/person-model/person';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonService } from '../modules/person-model/person.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private router: Router,private personservice : PersonService) { 
    localStorage.setItem('data', "");
  }
  
  login(){
    if(this.Email && this.Password)
      this.personservice.getPerson(this.Email, this.Password).subscribe({
        next: (v) => {
          this.user =v;
          //alert(v.deposit);
        },
        error: (e) => {console.error(e)},
        complete: () => {
          //alert(this.user?.user_name);
                
                localStorage.setItem('data', JSON.stringify(this.user));
                this.router.navigate(['/process']);
        }
        });
          
      
      else
      alert("Credintial  Error   ....   !")

     /*1.this.accountservice.setData(this.account);
      this.router.navigate(['/atm']);*/

      /*localStorage.setItem('User', JSON.stringify(this.person1));
      this.router.navigate(['/process']);*/

     //3. this.router.navigate(['/atm'], { queryParams: { data: JSON.stringify(this.account) } });


  }



  openSidebar() {
    this.router.navigate(['/atm-login']);
  }

  Email!:string;
  Password!:string;
  user?:Person;

}
