import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductModel} from '../_models/product.model';
import {ProductService} from '../_services/product.service';
import {CategoryModel} from '../_models/category.model';
import {CategoryService} from '../_services/category.service';
import * as Noty from 'noty';
import {TranslateService} from '../_services/translate.service';


declare let $: any;

@Component({
  selector: 'app-purchase-form',
  templateUrl: './purchase-form.component.html',
  styleUrls: ['./purchase-form.component.scss']
})
export class PurchaseFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private productService: ProductService,
              private categoryService: CategoryService,
              private translateService: TranslateService) {
  }

  @Input() orderForm: FormGroup;
  purchases: FormArray = null;
  products: ProductModel[] = null;
  taxType = null;
  selectedProduct: any = null;
  productForm: FormGroup = null;
  submittedProduct: boolean = null;
  errorProduct: boolean = null;
  selectedCategorie: any = null;
  categories: CategoryModel[] = null;
  totalTTC: number = null;
  totalTVA: number = null;
  totalHT: number = null;


  ngOnInit() {
    this.taxType = 0;
    this.submittedProduct = false;
    /*/
    this.orderForm = this.formBuilder.group({

      purchases: this.formBuilder.array([ this.createItem([this.formBuilder.control('', Validators.required)]) ])
    });*/
    this.productService.getAllProducts()
      .subscribe(
        (products: any) => {
          this.products = products;
        });

    this.categoryService.getAllCategories()
      .subscribe(
        (categories: CategoryModel[]) => {
          this.categories = categories;
        });


    this.productForm = this.formBuilder.group({
      reference: ['', Validators.required],
      designation: ['', Validators.required],
      category: ['']

      // @ts-ignore
    });

    this.categoryService.getAllCategories()
      .subscribe(
        (categories: CategoryModel[]) => {
          this.categories = categories;
          this.categories = categories;
        });

  }

  get purchase() {
    return this.orderForm.get('purchases') as FormArray
  }

  createItem(): FormGroup {
    return this.formBuilder.group({
      product: ['', Validators.required],
      quantity: ['', Validators.required],
      unitPrice: ['', Validators.required],
      discount: ['', Validators.required],
      tva: ['19', Validators.required],
      unitPriceAfterDiscount: [''],
      amountHT: ['',],
      amountTVA: ['',],
      amountTTC: ['',],
    });
  }

  addItem(): void {
    this.purchases = this.orderForm.get('purchases') as FormArray;
    this.purchases.push(this.createItem());
  }

  submit() {
    console.log(this.orderForm.value);
  }

  addProduct() {
    this.productService.postProduct(this.productForm.value)
      .subscribe((data: ProductModel) => {

          this.products.push(data);
          $('#Product').modal('toggle');

          new Noty({
            theme: 'metroui',
            type: 'success',
            timeout: 5000,
            text: this.translateService.data['PRODUCT_ADD_SUCCESS'] || 'PRODUCT_ADD_SUCCESS'
          }).show();

        }
        ,
        () => {
          new Noty({
            theme: 'metroui',
            type: 'error',
            timeout: 5000,
            text: this.translateService.data['PRODUCT_ADD_ERROR'] || 'PRODUCT_ADD_ERROR'
          }).show();
        }
      );


  }

  editTax(taxType): void {
    this.taxType = taxType;
  }

  calculateTotals() {
    this.totalTTC = 0;
    this.totalHT = 0;
    this.totalTVA = 0;

    for (let i = 0; i < this.orderForm.get('purchases').value.length; i++) {
      this.totalTTC += this.calculateTTC(i);
      this.totalHT += this.calculateHT(i);
      this.totalTVA += this.calculateTVA(i);
    }

    console.log(this.totalTTC);
    this.orderForm.get('totalHT').setValue(this.totalHT);
    this.orderForm.get('totalTTC').setValue(this.totalTTC);
    this.orderForm.get('totalTVA').setValue(this.totalTVA);

    // this.totalTTC += this.bill.taxStamp;


  }

  calculatePrices(i): any {
    this.calculateDiscount(i);
    this.calculateHT(i);
    this.calculateTVA(i);
    this.calculateTotals();
  }

  calculateDiscount(i): number {
    const newPrice = this.orderForm.get('purchases').value[i].unitPrice - this.orderForm.get('purchases').value[i].unitPrice * this.orderForm.get('purchases').value[i].discount / 100;
    this.orderForm.get('purchases').value[i].unitPriceAfterDiscount = parseFloat(newPrice.toFixed(3));
    return parseFloat(newPrice.toFixed(3));
  }

  calculateTTC(i): number {

    let amountTTC;
    amountTTC = this.calculateTVA(i) + this.calculateHT(i);
    this.orderForm.get('purchases').value[i].amountTTC = parseFloat(amountTTC.toFixed(3));
    return parseFloat(amountTTC.toFixed(3));
  }

  calculateTVA(i): number {

    let amountTVA;
    if (this.taxType === 0) {
      amountTVA = this.orderForm.get('purchases').value[i].unitPriceAfterDiscount * this.orderForm.get('purchases').value[i].tva / 100 * this.orderForm.get('purchases').value[i].quantity;
      this.orderForm.get('purchases').value[i].amountTVA = parseFloat(amountTVA.toFixed(3));
      return parseFloat(amountTVA.toFixed(3));
    }
    amountTVA = (this.orderForm.get('purchases').value[i].unitPriceAfterDiscount - this.orderForm.get('purchases').value[i].unitPriceAfterDiscount / (1 + (this.orderForm.get('purchases').value[i].tva / 100))) * this.orderForm.get('purchases').value[i].quantity;
    this.orderForm.get('purchases').value[i].amountTVA = parseFloat(amountTVA.toFixed(3));
    return parseFloat(amountTVA.toFixed(3));
  }

  calculateHT(i): number {

    let amountHT;
    if (this.taxType === 0) {
      amountHT = this.orderForm.get('purchases').value[i].unitPriceAfterDiscount * this.orderForm.get('purchases').value[i].quantity;
      this.orderForm.get('purchases').value[i].amountHT = parseFloat(amountHT.toFixed(3));
      return parseFloat(amountHT.toFixed(3));
    }
    amountHT = (this.orderForm.get('purchases').value[i].unitPriceAfterDiscount) * this.orderForm.get('purchases').value[i].quantity - this.orderForm.get('purchases').value[i].amountTVA;
    this.orderForm.get('purchases').value[i].amountHT = parseFloat(amountHT.toFixed(3));
    return parseFloat(amountHT.toFixed(3));
  }

  removePurchase(i) {
    // @ts-ignore
    this.orderForm.get('purchases').removeAt(i);
    this.calculateTotals();
  }


  calculateAllPrices() {
    for (let i = 0; i < this.products.length; i++) {
      this.calculatePrices(i);
    }
    this.calculateTotals();
  }


}
