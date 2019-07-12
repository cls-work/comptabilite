import {Component, OnInit} from '@angular/core';
import {QuotationService} from '../_services/quotation.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-confirm-quotation',
  templateUrl: './confirm-quotation.component.html',
  styleUrls: ['./confirm-quotation.component.scss']
})
export class ConfirmQuotationComponent implements OnInit {

  quotationId: string;

  constructor(private quotationService: QuotationService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.quotationId = this.route.snapshot.params['quotationId'];


  }

  onConfirmClick() {
    this.confirmQuotation();
  }

  confirmQuotation() {
    this.quotationService.confirmQuotationById(this.quotationId).subscribe(
      (data) => {
        console.log('success confirm');
        this.router.navigate(['quotations/list']);

      }, (error) => {
        console.log('errreur ');

      }
    );

  }


  rejectQuotation() {
    this.quotationService.rejectQuotationById(this.quotationId).subscribe(
      (data) => {
        console.log('success reject');
        this.router.navigate(['quotations/list']);

      }, (error) => {
        console.log('erreur');
      }
    );

  }

}
