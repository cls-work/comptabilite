import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AddProductsComponent implements OnInit {

  productsForm: FormGroup ;
  bill: BillModel;
  // taxType=0 if HT, ==1 if ttc
  taxType;
  totalTTC: number;
  totalTVA: number;
  totalHT: number;

  loading: boolean;
  new: boolean;
  submitted: boolean;
  error: boolean;

  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit() {/*
    // this.loading = true;
    this.billService.getBillByID(this.route.snapshot.params.id)
      .subscribe(bill => {

        // @ts-ignore
        this.bill = bill;
        this.calculateTotals();

        this.loading = false;
      }, () => {
        this.error = true;
        this.loading = false;

      });

    this.productsForm = this.formBuilder.group(
      {
        products: this.formBuilder.array([

            this.productForm()
          ])
      });

    this.taxType = 0;

    this.new = this.route.snapshot.params.success === 'new';
*/
  }

  /*
    productForm() {
      return this.formBuilder.group({
        designation: ['', Validators.required],
        quantity: ['', Validators.required],
        unitPrice: ['', Validators.required],
        discount: [0, Validators.required],
        tva: [19, Validators.required],
        unitPriceAfterDiscount: ['0', Validators.required],
        amountHT: [0, Validators.required],
        amountTVA: [0, Validators.required],
        amountTTC: [0, Validators.required]
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

    pushProducts() {
      this.submitted = true;
      this.loading = true;
      const billId = this.route.snapshot.params.id;
      this.billService.postProducts(billId, this.productsForm.value.products)
        .subscribe(d => {
          this.router.navigate(['/bill-detail', billId]);
          this.loading = false;
        }, () => {
          this.error = true;
          this.loading = false;
      });
    }





    calculateTotals() {

      this.totalHT = this.bill.totalHT;
      this.totalTVA = this.bill.totalTVA;
      this.totalTTC = this.bill.totalTTC;

      for (let i = 0; i < this.products.value.length; i++) {
        this.totalTTC += this.calculateTTC(i);
        this.totalHT += this.calculateHT(i);
        this.totalTVA += this.calculateTVA(i);
      }

      this.totalTTC += this.bill.taxStamp;


    }

    editTax(taxType: number) {
      this.taxType = taxType;
    }

    calculatePrices(i) {
      this.calculateDiscount(i);
      this.calculateHT(i);
      this.calculateTVA(i);
      this.calculateTotals();
    }

    calculateDiscount(i) {
      const newPrice = this.products.value[i].unitPrice - this.products.value[i].unitPrice * this.products.value[i].discount / 100;
      this.products.value[i].unitPriceAfterDiscount = parseFloat(newPrice.toFixed(3));
      return newPrice.toFixed(3);
    }

    calculateTVA(i) {
      let amountTVA;
      if (this.taxType === 0) {
        amountTVA = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].tva / 100 * this.products.value[i].quantity;
        this.products.value[i].amountTVA =  parseFloat(amountTVA.toFixed(3));
        return parseFloat(amountTVA.toFixed(3));
      }
      amountTVA = (this.products.value[i].unitPriceAfterDiscount - this.products.value[i].unitPriceAfterDiscount / (1 + (this.products.value[i].tva / 100))) * this.products.value[i].quantity;
      this.products.value[i].amountTVA = parseFloat(amountTVA.toFixed(3));
      return parseFloat(amountTVA.toFixed(3));
    }

    calculateHT(i: number) {
      let amountHT;
      if (this.taxType === 0) {
        amountHT = this.products.value[i].unitPriceAfterDiscount * this.products.value[i].quantity;
        this.products.value[i].amountHT = parseFloat(amountHT.toFixed(3));
        return parseFloat(amountHT.toFixed(3));
      }
      amountHT = (this.products.value[i].unitPriceAfterDiscount ) * this.products.value[i].quantity - this.products.value[i].amountTVA;
      this.products.value[i].amountHT = parseFloat(amountHT.toFixed(3));
      return parseFloat(amountHT.toFixed(3));
    }

    calculateTTC(i: number) {
      let amountTTC;
      amountTTC = this.calculateTVA(i) + this.calculateHT(i);

      this.products.value[i].amountTTC = parseFloat(amountTTC.toFixed(3));
      return parseFloat(amountTTC.toFixed(3));
    }

    calculateAllPrices() {
      for (let i = 0; i < this.products.value; i++) {
        this.calculatePrices(i);
      }
      this.calculateTotals();
    }
    */

}
