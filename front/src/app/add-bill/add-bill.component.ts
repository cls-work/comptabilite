import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-bill',
  templateUrl: './add-bill.component.html',
  styleUrls: ['./add-bill.component.scss']
})
export class AddBillComponent implements OnInit {
  labelPosition: boolean;
  cheque = true;
  quotation_id: any;
  quotationForm: FormGroup;
  checkform: FormGroup;

  constructor(
    private formBuilder: FormBuilder, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.quotation_id = this.route.snapshot.params.id;
    this.quotationForm = this.formBuilder.group({
      id: [this.quotation_id]

      // @ts-ignore
    });
  }

  editcheck(number: any) {
    this.cheque = number;
  }

}
