import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.scss']
})
export class AddBillComponent implements OnInit {

  addBillForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private billService: BillService, private router: Router) { }

  ngOnInit() {
    this.addBillForm = this.formBuilder.group({
      provider: ['', Validators.required],
      date: ['', Validators.required],
      taxStamp: ['', Validators.required],
      checkPayment: ['0', Validators.required],
      checkReference: ''
    });
  }

  addBill() {
    this.addBillForm.value.checkPayment = parseInt(this.addBillForm.value.checkPayment, 10);
    if (this.addBillForm.value.checkPayment === 0) {
      this.addBillForm.value.checkRefeerence = '';
    }
    // @ts-ignore
    this.billService.postBill(this.addBillForm.value)
      .subscribe(d =>
        // @ts-ignore
        this.router.navigate(['/add-products', d.billId])
      );
  }

  editCheckPayment() {
    return parseInt(this.addBillForm.value.checkPayment, 10) !== 0;

  }
}
