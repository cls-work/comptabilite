import {Component, OnInit} from '@angular/core';
import {QuotationService} from '../_services/quotation.service';
import {ActivatedRoute, Router} from '@angular/router';
import * as Noty from 'noty';
import {TranslateService} from '../_services/translate.service';
import {AuthenticationService} from '../_services/authentication.service';
import {ROLE_ADMIN} from '../_globals/vars';

@Component({
  selector: 'app-confirm-quotation',
  templateUrl: './confirm-quotation.component.html',
  styleUrls: ['./confirm-quotation.component.scss']
})
export class ConfirmQuotationComponent implements OnInit {

  quotationId: string;
  currentUser;

  constructor(private quotationService: QuotationService,
              private route: ActivatedRoute,
              private router: Router,
              private authService: AuthenticationService,
              private translateService: TranslateService) {
  }

  ngOnInit() {
    this.quotationId = this.route.snapshot.params['quotationId'];
    this.authService.currentUser
      .subscribe(x => {
        this.currentUser = x;
        console.log(this.currentUser);
      });

  }
  
  get roleAdmin(){
    return ROLE_ADMIN;
  }

  onConfirmClick() {
    this.confirmQuotation();
  }

  confirmQuotation() {
    this.quotationService.confirmQuotationById(this.quotationId).subscribe(
      (data) => {
        new Noty({
          theme: 'metroui',
          type: 'success',
          timeout: 5000,
          text: this.translateService.data['BILL_CONFIRM_SUCCESS']
        }).show();
        this.router.navigate(['quotations/list']);

      }, (error) => {
        console.log('errreur ');
        new Noty({
          theme: 'metroui',
          type: 'error',
          timeout: 5000,
          text: this.translateService.data['BILL_CONFIRM_ERROR']
        }).show();
      }
    );

  }


  rejectQuotation() {
    this.quotationService.rejectQuotationById(this.quotationId).subscribe(
      (data) => {
        console.log('success reject');
        new Noty({
          theme: 'metroui',
          type: 'success',
          timeout: 5000,
          text: this.translateService.data['BILL_REJECT_SUCCESS']
        }).show();
        this.router.navigate(['quotations/list']);

      }, (error) => {
        console.log('erreur');
        new Noty({
          theme: 'metroui',
          type: 'error',
          timeout: 5000,
          text: this.translateService.data['BILL_REJECT_ERROR']
        }).show();
      }
    );

  }

}
