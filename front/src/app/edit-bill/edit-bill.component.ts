import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-edit-bill',
  templateUrl: './edit-bill.component.html',
  styleUrls: ['./edit-bill.component.scss']
})
export class EditBillComponent implements OnInit {


  private editBillForm: FormGroup;
  isCheckPayment = false;
  bill: BillModel;
  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.billService.getBillByID(this.route.snapshot.params.id)
      .subscribe(bill => {
        // @ts-ignore
        this.bill = bill;
        console.log(this.bill);

        // @ts-ignore
        this.editBillForm = this.formBuilder.group({
          provider: [this.bill.provider, Validators.required],
          date: [this.bill.date, Validators.required],
          taxStamp: [this.bill.taxStamp, Validators.required],
          // @ts-ignore
          checkPayment: [this.bill.checkPayment === true ? '1' : '0', Validators.required],
          checkReference: [this.bill.checkReference],
        });
      });
  }



  editBill() {

    const billId = this.route.snapshot.params.id;


    this.editBillForm.value.checkPayment = parseInt(this.editBillForm.value.checkPayment, 10);
    if (this.editBillForm.value.checkPayment === 0) {
      this.editBillForm.value.checkRefeerence = '';
    }
    this.billService.editBill(billId, Object.assign({billId}, this.editBillForm.value))
      .subscribe(d => {
        this.router.navigate(['/bills']);
      });

  }

  editCheckPayment() {
    return parseInt(this.editBillForm.value.checkPayment, 10) !== 0;
  }
}
