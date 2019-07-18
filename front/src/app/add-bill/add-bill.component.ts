import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {BillService} from '../_services/bill.service';

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
    private formBuilder: FormBuilder, private route: ActivatedRoute ,
    private  billService: BillService) {
  }

  ngOnInit() {
    this.quotation_id = Number(this.route.snapshot.params.id);
    this.quotationForm = this.formBuilder.group({
      id: [this.quotation_id]
    });

    this.checkform = this.formBuilder.group({
      amount: ['', Validators.required],
      bankAccount: ['', Validators.required],
      checkState: ['', Validators.required],
      profilOf: ['', Validators.required],
      reference: ['', Validators.required]

      // @ts-ignore
    });

  }

  editcheck(number: any) {
    this.cheque = number;
  }

  addBill() {

    this.billform = this.formBuilder.group({
      checkPayment: [this.checkform.value],
      quotation: [this.quotationForm.value]
      // @ts-ignore
    });

    this.billService.postBill(this.billform.value).subscribe((res) => {
      console.log(res) ;
    }) ;
    console.log(this.billform.value);
  }
}
