import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from '@angular/forms';
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
  totalTTC;
  totalTVA;
  totalHT;


  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private route: ActivatedRoute) {}

  ngOnInit() {

    this.billService.getBillByID(this.route.snapshot.params.id)
      .then(bill => {
        this.bill = bill;
        this.calculateTotals();
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
      designation: '',
      quantity: '0',
      unitPrice: '0',
      discount: '0',
      tva: '19',
      unitPriceAfterDiscount: '0',
      amountHT: '0',
      amountTVA: '0',
      amountTTC: '0'
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

    this.totalHT=this.bill.totalHT;
    this.totalTVA=this.bill.totalTVA;
    this.totalTTC=this.bill.totalTTC;

    for (let i = 0; i < this.products.value.length; i++) {
      console.log('total',i)
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
    return newPrice;
  }

  calculateTVA(i) {
    if (this.taxType === 0) {
      this.products.value[i].amountTVA = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].tva / 100;
      return this.products.value[i].unitPriceAfterDiscount * this.products.value[i].tva / 100;
    }
    this.products.value[i].amountTVA = (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].unitPriceAfterDiscount / (1 + (this.products.value[i].tva / 100)));
    return this.products.value[i].unitPriceAfterDiscount - this.products.value[i].unitPriceAfterDiscount / (1 + (this.products.value[i].tva / 100));
  }

  calculateHT(i: number) {
    if (this.taxType === 0) {
      this.products.value[i].amountHT = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
      return this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
    }
    console.log(this.products.value[i].amountTVA, this.products.value[i].unitPriceAfterDiscount, this.products.value[i].unitPriceAfterDiscount - this.products.value[i].amountTVA);
    this.products.value[i].amountHT = (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].amountTVA ) * this.products.value[i].quantity;
    return (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].amountTVA ) * this.products.value[i].quantity;
  }

  calculateTTC(i: number) {
    if (this.taxType === 1) {
      this.products.value[i].amountTTC = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
      return this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
    }
    this.products.value[i].amountTTC = (this.products.value[i].unitPriceAfterDiscount + this.products.value[i].amountTVA ) * this.products.value[i].quantity;
    return (this.products.value[i].unitPriceAfterDiscount + this.products.value[i].amountTVA ) * this.products.value[i].quantity;
  }

  calculateAllPrices() {
    for (let i = 0; i < this.products.value; i++) {
      this.calculatePrices(i);
    }
  }
}
