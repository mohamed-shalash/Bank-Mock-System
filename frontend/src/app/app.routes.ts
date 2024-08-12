import { Routes } from '@angular/router';
import { ATMComponent } from './atm/atm.component';
import { LoginComponent } from './login/login.component';
import { BalanceComponent } from './atm/balance/balance.component';
import { DrawComponent } from './atm/draw/draw.component';
import { DepositComponent } from './atm/deposit/deposit.component';
import { MainComponent } from './atm/main/main.component';
import { AccountComponent } from './account/account.component';
import { AddUserComponent } from './add-user/add-user.component';
import { IntermediateComponent } from './intermediate/intermediate.component';
import { PersonComponent } from './person/person.component';
import { LogsComponent } from './logs/logs.component';
import { AddAccountComponent } from './account/add-account/add-account.component';

export const routes: Routes = [
    {path:"atm-login", component:ATMComponent},
    {path:"balance", component:BalanceComponent},
    {path:"draw", component:DrawComponent},
    {path:"deposit", component:DepositComponent},
    {path:"atm", component:MainComponent},
    {path:"account", component:AccountComponent},
    {path:"add-user", component:AddUserComponent},
    {path:"process", component:IntermediateComponent},
    {path:"person", component:PersonComponent},
    {path:"log", component:LogsComponent},
    {path:"add-acount", component:AddAccountComponent},
    {path:"" ,component:LoginComponent}
];
