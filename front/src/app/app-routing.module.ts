import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';

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
    path: '**',
    redirectTo: 'bills',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
