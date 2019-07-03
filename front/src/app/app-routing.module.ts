import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';
import {AddProductsComponent} from './add-products/add-products.component';
import {BillFormComponent} from './bill-form/bill-form.component';
import {LoginComponent} from './login/login.component';
import {UserFormComponent} from './user-form/user-form.component';
import {UsersDetailsComponent} from './users-details/users-details.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {AuthGuard} from './_guards/auth.guard';
import {AdminGuard} from './_guards/admin.guard';
import {GuestGuard} from './_guards/guest.guard';
import {HistoricalsListComponent} from './historicals-list/historicals-list.component';
import {AddQuotationComponent} from './add-quotation/add-quotation.component';
import {ListQuotationComponent} from './list-quotation/list-quotation.component';

const routes: Routes = [
  {
    path: 'bills',
    component: BillsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'bills/:edited',
    component: BillsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'bill-detail/:id',
    component: BillDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'bill-form',
    component: BillFormComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'bill-form/:id',
    component: BillFormComponent,
    canActivate: [AuthGuard]
  },


  {
    path: 'users',
    component: UsersDetailsComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'users/:success',
    component: UsersDetailsComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'user-form',
    component: UserFormComponent,
    canActivate: [AuthGuard, AdminGuard]
  },
  {
    path: 'user-form/:id',
    component: UserFormComponent,
    canActivate: [AuthGuard, AdminGuard]
  }, {
    path: 'quotations/add',
    component: AddQuotationComponent
  }, {
    path: 'quotations/list',
    component: ListQuotationComponent
  },


  {
    path: 'historicals',
    component: HistoricalsListComponent,
    canActivate: [AuthGuard, AdminGuard]
  },


  {
    path: 'add-products/:id',
    component: AddProductsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'add-products/:id/:success',
    component: AddProductsComponent,
    canActivate: [AuthGuard]
  },


  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'reset-password',
    component: ResetPasswordComponent,
    canActivate: [GuestGuard]
  },
  {
    path: 'reset-password/:token',
    component: ResetPasswordComponent,
    canActivate: [GuestGuard]
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
export class AppRoutingModule {
}
