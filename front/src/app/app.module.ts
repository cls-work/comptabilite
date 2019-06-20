import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BillsComponent } from './bills/bills.component';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { AddUserComponent } from './add-user/add-user.component';
import { LoginComponent } from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorInterceptor} from './_helpers/error.interceptor';
import {JwtInterceptor} from './_helpers/jwt.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BillService} from './_services/bill.service';
import {User} from './_models/user.model';
import {UserService} from './_services/user.service';
import {AuthenticationService} from './_services/authentication.service';
import { AddBillComponent } from './add-bill/add-bill.component';
import { AddProductsComponent } from './add-products/add-products.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { EditBillComponent } from './edit-bill/edit-bill.component';
import {DisableControlDirective} from './_directives/disable-controle.directive';
import {FilterPipe} from './_pipes/filter.pipe';
import {OrderByPipe} from './_pipes/order-by.pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    BillsComponent,
    BillDetailsComponent,
    AddUserComponent,
    LoginComponent,
    AddBillComponent,
    AddProductsComponent,
    ProductDetailsComponent,
    EditBillComponent,
    DisableControlDirective,
    FilterPipe,
    OrderByPipe,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxPaginationModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    BillService,
    UserService,
    AuthenticationService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
