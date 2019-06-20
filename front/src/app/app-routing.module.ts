import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';
import {AddProductsComponent} from './add-products/add-products.component';
import {BillFormComponent} from './bill-form/bill-form.component';
import {LoginComponent} from './login/login.component';
import {UserFormComponent} from './user-form/user-form.component';
import {UsersDetailsComponent} from './users-details/users-details.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';

const routes: Routes = [
  {
    path: 'bills',
    component: BillsComponent,
    // canActivate: [AuthGuard]
  },
  {
    path: 'bill-detail/:id',
    component: BillDetailsComponent,
  },
  {
    path: 'bill-form',
    component: BillFormComponent,
  },
  {
    path: 'bill-form/:id',
    component: BillFormComponent,
  },



  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'users',
    component: UsersDetailsComponent,
  },
  {
    path: 'user-form',
    component: UserFormComponent,
  },
  {
    path: 'user-form/:id',
    component: UserFormComponent,
  },
  {
    path: 'reset-password/:token',
    component: ResetPasswordComponent,
  },



  {
    path: 'add-products/:id',
    component: AddProductsComponent,
  },



  {
    path: '**',
    redirectTo: 'bills',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
