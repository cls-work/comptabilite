import {Component, Input, OnInit} from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ProductModel} from '../_models/product.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute} from '@angular/router';
import {Validators} from '@angular/forms';

@Component({
  selector: 'app-bill-details',
  templateUrl: './bill-details.component.html',
  styleUrls: ['./bill-details.component.scss']
})
export class BillDetailsComponent implements OnInit {


  products: ProductModel[];
  bill: BillModel;
  loading: boolean;
  nbGet: number;

  constructor(private billService: BillService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.nbGet = 0;

    this.getBill();

    this.getAllProducts();
  }


  productChange() {

      this.getAllProducts();
      this.getBill();

  }

  getAllProducts() {
    this.loading = true;
    this.nbGet++;
    this.billService.getProductsByBillId(this.route.snapshot.params.id)
      .subscribe(products => {
        // @ts-ignore
        this.products = products;
        console.log(this.products);
        this.nbGet--;
        if (this.nbGet === 0) {
          this.loading = false;
        }
      });
  }

  getBill() {
    this.loading = true;
    this.nbGet++;
    this.billService.getBillByID(this.route.snapshot.params.id)
      .subscribe(bill => {
        // @ts-ignore
        this.bill = bill;
        console.log(this.bill);
        this.nbGet--;
        if (this.nbGet === 0) {
          this.loading = false;
        }

      });
  }
  deleteProduct(productId: string) {
    if (confirm('delete this products?')) {
      this.loading = true;
      this.billService.deleteProduct(productId)
        .subscribe(d => {
          console.log(d);
          // this.productChange.emit(true);
          this.productChange();
        });
    }

  }
}
