import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.scss']
})
export class AddBillComponent implements OnInit {

  addBillForm: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.addBillForm = this.formBuilder.group({
      provider: ['', Validators.required],
      date: ['', Validators.required],
      taxStamp: ['', Validators.required],
      paymentMethod: ['0', Validators.required]
    });
  }

  addBill() {
    console.log('hi', this.addBillForm.value);
  }
}
