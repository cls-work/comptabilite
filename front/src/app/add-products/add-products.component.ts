import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AddProductsComponent implements OnInit {

  productsForm: FormGroup ;
  bill: BillModel;
  taxType = 0;
  totalTTC: number;
  totalTVA: number;
  totalHT: number;


  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private route: ActivatedRoute) {}

  ngOnInit() {

    this.billService.getBillByID(this.route.snapshot.params.id)
      .then(bill => {
        this.bill = bill;
        this.calculateTotals();
        console.log(typeof (this.bill.totalTTC));
      });

    this.productsForm = this.formBuilder.group(
      {
        products: this.formBuilder.array([

            this.productForm()
          ])
      });



  }

  productForm() {
    return this.formBuilder.group({
      designation: ['', Validators.required],
      quantity: ['', Validators.required],
      unitPrice: ['', Validators.required],
      discount: ['0', Validators.required],
      tva: ['19', Validators.required],
      unitPriceAfterDiscount: ['0', Validators.required],
      amountHT: ['0', Validators.required],
      amountTVA: ['0', Validators.required],
      amountTTC: ['0', Validators.required]
    });
  }

  get products() {
    return this.productsForm.get('products') as FormArray;
  }

  addProduct() {
    this.products.push(
      this.productForm()
    );
  }

  removeProduct(i) {
    this.products.removeAt(i);
    this.calculateTotals();
  }

  calculateTotals() {

    this.totalHT = this.bill.totalHT;
    this.totalTVA = this.bill.totalTVA;
    this.totalTTC = this.bill.totalTTC;

    for (let i = 0; i < this.products.value.length; i++) {
      console.log('total', i);
      this.totalTTC += this.calculateTTC(i);
      this.totalHT += this.calculateHT(i);
      this.totalTVA += this.calculateTVA(i);
    }


  }

  pushProducts() {
    console.log(this.productsForm.value.products);
  }

  editTax(taxType: number) {
    this.taxType = taxType;
    console.log(this.taxType);
  }

  calculatePrices(i) {
    this.calculateDiscount(i);
    this.calculateTVA(i);
    this.calculateHT(i);
    this.calculateTotals();
    console.log(this.products.value[i]);
  }

  calculateDiscount(i) {
    const newPrice = this.products.value[i].unitPrice - this.products.value[i].unitPrice * this.products.value[i].discount / 100;
    this.products.value[i].unitPriceAfterDiscount = newPrice;
    return newPrice.toFixed(3);
  }

  calculateTVA(i) {
    if (this.taxType === 0) {
      const amountTVA = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].tva / 100;
      this.products.value[i].amountTVA =  amountTVA;
      return parseFloat(amountTVA.toFixed(3));
    }
    const amountTVA = (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].unitPriceAfterDiscount / (1 + (this.products.value[i].tva / 100)));
    this.products.value[i].amountTVA = amountTVA;
    return parseFloat(amountTVA.toFixed(3));
  }

  calculateHT(i: number) {
    let amountHT;
    if (this.taxType === 0) {
      amountHT = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
      this.products.value[i].amountHT = amountHT;
      return parseFloat(amountHT.toFixed(3));
    }
    amountHT = (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].amountTVA ) * this.products.value[i].quantity;
    this.products.value[i].amountHT = amountHT;
    return parseFloat(amountHT.toFixed(3));
  }

  calculateTTC(i: number) {
    let amountTTC;
    if (this.taxType === 1) {
      amountTTC = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
      this.products.value[i].amountTTC = amountTTC;
      return parseFloat(amountTTC.toFixed(3));
    }
    amountTTC=(this.products.value[i].unitPriceAfterDiscount + this.products.value[i].amountTVA ) * this.products.value[i].quantity
    this.products.value[i].amountTTC = amountTTC;
    return parseFloat(amountTTC.toFixed(3));
  }

  calculateAllPrices() {
    for (let i = 0; i < this.products.value; i++) {
      this.calculatePrices(i);
    }
  }
}
