import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-bill-form',
  templateUrl: './bill-form.component.html',
  styleUrls: ['./bill-form.component.scss']
})
export class BillFormComponent implements OnInit {


  billForm: FormGroup;
  isCheckPayment = false;
  bill: BillModel;
  loading: boolean;
  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.loading = true;

    const id = this.route.snapshot.params.id;


    this.billForm = this.formBuilder.group({
      provider: ['', Validators.required],
      date: ['', Validators.required],
      taxStamp: ['', Validators.required],
      // @ts-ignore
      checkPayment: ['0', Validators.required],
      checkReference: [''],
    });

    if (id) {

      this.billService.getBillByID(this.route.snapshot.params.id)
        .subscribe(bill => {
          // @ts-ignore
          this.bill = bill;
          console.log(this.bill);

          // @ts-ignore
          this.billForm = this.formBuilder.group({
            provider: [this.bill.provider, Validators.required],
            date: [this.bill.date, Validators.required],
            taxStamp: [this.bill.taxStamp, Validators.required],
            // @ts-ignore
            checkPayment: [this.bill.checkPayment === true ? '1' : '0', Validators.required],
            checkReference: [this.bill.checkReference],
          });
          this.loading = false;
        });
    } else {
      this.loading = false;
    }

  }



  editBill() {
    this.loading = true;

    const billId = this.route.snapshot.params.id;


    this.billForm.value.checkPayment = parseInt(this.billForm.value.checkPayment, 10);
    if (this.billForm.value.checkPayment === 0) {
      this.billForm.value.checkRefeerence = '';
    }
    this.billService.editBill(billId, Object.assign({billId}, this.billForm.value))
      .subscribe(d => {
        this.loading = false;
        this.router.navigate(['/bills']);
      });

  }

  addBill() {
    this.loading = true;
    this.billForm.value.checkPayment = parseInt(this.billForm.value.checkPayment, 10);
    if (this.billForm.value.checkPayment === 0) {
      this.billForm.value.checkRefeerence = '';
    }
    // @ts-ignore
    this.billService.postBill(this.billForm.value)
      .subscribe(d => {
        this.loading = false;
        // @ts-ignore
        this.router.navigate(['/add-products', d.billId]);
      });
  }

  editCheckPayment() {
    return parseInt(this.billForm.value.checkPayment, 10) !== 0;
  }
}
