import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.scss']
})
export class AddProductsComponent implements OnInit {

  productsForm: FormGroup ;
  bill: BillModel;

  constructor(private formBuilder: FormBuilder, private billService: BillService, private route: ActivatedRoute) {}

  ngOnInit() {

    this.billService.getBillByID(this.route.snapshot.params.id)
      .then(bill => {
        this.bill = bill;
      });

    this.productsForm = this.formBuilder.group(
      {
        products: this.formBuilder.array([

            this.formBuilder.group({
              designation: '',
              quantity: '',
              unitPrice: '',
              discount: '',
              tva: ''
            })
          ])
      });

  }

  get products() {
    return this.productsForm.get('products') as FormArray;
  }

  addProduct() {
    this.products.push(this.formBuilder.group({
     designation: '',
        quantity: '',
      unitPrice: '',
      discount: '',
      tva: ''
    }));


  }

  pushProducts() {
    console.log(this.productsForm.value.products);
  }
}
