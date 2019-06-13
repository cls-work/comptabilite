import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';
import {AddBillComponent} from './add-bill/add-bill.component';
import {AddProductsComponent} from './add-products/add-products.component';

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
