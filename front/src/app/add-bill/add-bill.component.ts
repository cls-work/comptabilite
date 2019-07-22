import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillService} from '../_services/bill.service';
import * as Noty from 'noty';
import {TranslateService} from '../_services/translate.service';

@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.scss']
})
export class AddBillComponent implements OnInit {
  labelPosition: boolean;
  cheque = true;
  quotation_id: number;
  quotationForm: FormGroup;
  checkform: FormGroup;
  billform: FormGroup;

  constructor(
    private formBuilder: FormBuilder, private route: ActivatedRoute,
    private  billService: BillService,
    private translateService: TranslateService,
    private router: Router) {
  }

  ngOnInit() {
    this.quotation_id = Number(this.route.snapshot.params.quotationId);
    this.quotationForm = this.formBuilder.group({
      id: [this.quotation_id]
    });

    this.checkform = this.formBuilder.group({
      amount: ['', Validators.required],
      bankAccount: ['', Validators.required],
      checkState: ['', Validators.required],
      profilOf: ['', Validators.required],
      reference: ['', Validators.required],
      checkDate: ['', Validators.required]

      // @ts-ignore
    });

  }

  editcheck(number: any) {
    this.cheque = number;
  }

  addBill() {
    if (this.cheque) {
      this.billform = this.formBuilder.group({
        checkPayment: [this.checkform.value],
        quotation: [this.quotationForm.value]
        // @ts-ignore
      });
    } else {
      this.billform = this.formBuilder.group({
        quotation: [this.quotationForm.value]
        // @ts-ignore
      });
    }


    this.billService.postBill(this.billform.value).subscribe((res) => {
      new Noty({
        theme: 'metroui',
        type: 'success',
        timeout: 5000,
        text: this.translateService.data['BILL_CREATION_SUCCESS'] || 'BILL_CREATION_SUCCESS'
      }).show();
      this.router.navigate(['quotations/list']);
      console.log(res);
    }, (error) => {
      new Noty({
        theme: 'metroui',
        type: 'error',
        timeout: 5000,
        text: this.translateService.data['BILL_CREATION_ERROR'] || 'BILL_CREATION_ERROR'
      }).show();

    });
    console.log(this.billform.value);
  }
}
