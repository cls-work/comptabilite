import { BrowserModule } from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BillsComponent } from './bills/bills.component';
import { BillDetailsComponent } from './bill-details/bill-details.component';
import { UserFormComponent } from './user-form/user-form.component';
import { LoginComponent } from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorInterceptor} from './_helpers/error.interceptor';
import {JwtInterceptor} from './_helpers/jwt.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BillService} from './_services/bill.service';
import {UserModel} from './_models/user.model';
import {UserService} from './_services/user.service';
import {AuthenticationService} from './_services/authentication.service';
import { AddProductsComponent } from './add-products/add-products.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { BillFormComponent } from './bill-form/bill-form.component';
import {DisableControlDirective} from './_directives/disable-controle.directive';
import {FilterPipe} from './_pipes/filter.pipe';
import {OrderByPipe} from './_pipes/order-by.pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import { HeaderComponent } from './header/header.component';
import { UsersDetailsComponent } from './users-details/users-details.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import {BillComponent} from './bill/bill.component';
import {TranslateService} from './_services/translate.service';
import { TranslatePipe } from './_pipes/translate.pipe';


export function setupHeaderTranslateFactory(service: TranslateService) {
  return () => service.componentLang('fr', 'header');
}
export function setupBillsTranslateFactory(service: TranslateService) {
  return () => service.componentLang('fr', 'bills');
}

@NgModule({
  declarations: [
    AppComponent,
    BillsComponent,
    BillDetailsComponent,
    UserFormComponent,
    LoginComponent,
    AddProductsComponent,
    ProductDetailsComponent,
    BillFormComponent,
    DisableControlDirective,
    FilterPipe,
    OrderByPipe,
    HeaderComponent,
    UsersDetailsComponent,
    UserDetailsComponent,
    ResetPasswordComponent,
    BillComponent,
    TranslatePipe
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
    AuthenticationService,
    TranslateService,
    {
      provide: APP_INITIALIZER,
      useFactory: setupBillsTranslateFactory,
      deps: [ TranslateService ],
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: setupHeaderTranslateFactory,
      deps: [ TranslateService ],
      multi: true
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
