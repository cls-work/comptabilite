import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';
import {AddBillComponent} from './add-bill/add-bill.component';
import {AddProductsComponent} from './add-products/add-products.component';
import {EditBillComponent} from './edit-bill/edit-bill.component';
import {LoginComponent} from './login/login.component';
import {UserFormComponent} from './user-form/user-form.component';
import {UsersDetailsComponent} from './users-details/users-details.component';

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
    path: 'add-bill',
    component: AddBillComponent,
  },
  {
    path: 'edit-bill/:id',
    component: EditBillComponent,
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
