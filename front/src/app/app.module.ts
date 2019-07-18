import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule, Provider} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BillsComponent} from './bills/bills.component';
import {BillDetailsComponent} from './bill-details/bill-details.component';
import {UserFormComponent} from './user-form/user-form.component';
import {LoginComponent} from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorInterceptor} from './_helpers/error.interceptor';
import {JwtInterceptor} from './_helpers/jwt.interceptor';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BillService} from './_services/bill.service';
import {UserService} from './_services/user.service';
import {AuthenticationService} from './_services/authentication.service';
import {AddProductsComponent} from './add-products/add-products.component';
import {BillFormComponent} from './bill-form/bill-form.component';
import {DisableControlDirective} from './_directives/disable-controle.directive';
import {NgxPaginationModule} from 'ngx-pagination';
import {HeaderComponent} from './header/header.component';
import {UsersDetailsComponent} from './users-details/users-details.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {TranslateService} from './_services/translate.service';
import {TranslatePipe} from './_pipes/translate.pipe';
import {HistoricalsListComponent} from './historicals-list/historicals-list.component';
import {DataTablesModule} from 'angular-datatables';
import {HistoricalService} from './_services/historical.service';
import {LoaderComponent} from './loader/loader.component';
import {DisplayFileComponent} from './display-file/display-file.component';

import {QuotationService} from './_services/quotation.service';
import { QuotationFormComponent } from './quotation-form/quotation-form.component';
import { QuotationAddPurchasesComponent } from './quotation-add-purchases/quotation-add-purchases.component';
import {NgSelectModule} from '@ng-select/ng-select';
import {ProviderService} from './_services/provider.service';
import { ProviderFormComponent } from './provider-form/provider-form.component';
import { PurchaseFormComponent } from './purchase-form/purchase-form.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule, MatTabsModule} from '@angular/material';
import {MatFileUploadModule } from 'angular-material-fileupload';
import { DragDropDirective } from './drag-drop.directive';


export function setupTranslateFactory(service: TranslateService) {
  return () => service.use('en');
}

@NgModule({
  declarations: [
    AppComponent,
    BillsComponent,
    BillDetailsComponent,
    UserFormComponent,
    LoginComponent,
    AddProductsComponent,
    BillFormComponent,
    DisableControlDirective,
    HeaderComponent,
    UsersDetailsComponent,
    ResetPasswordComponent,
    TranslatePipe,
    HistoricalsListComponent,
    LoaderComponent,
    DisplayFileComponent,
    QuotationFormComponent,
    QuotationAddPurchasesComponent,
    ProviderFormComponent,
    PurchaseFormComponent,
    DragDropDirective
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxPaginationModule,
    DataTablesModule,
    NgSelectModule,
    MatTabsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFileUploadModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
    ProviderService,
    BillService,
    UserService,
    AuthenticationService,
    HistoricalService,
    TranslateService, {
      provide: APP_INITIALIZER,
      useFactory: setupTranslateFactory,
      deps: [TranslateService],
      multi: true
    }, /*
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
    },*/
    /*{
      provide: APP_INITIALIZER,
      useFactory: setupBillFormTranslateFactory,
      deps: [ TranslateService ],
      multi: true
    }*/
    QuotationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
