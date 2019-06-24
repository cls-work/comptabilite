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

  constructor(private billService: BillService,
              private route: ActivatedRoute) { }

  ngOnInit() {

    this.getBill();

    this.getAllProducts();
  }

  getAllProducts() {
    this.billService.getProductsByBillId(this.route.snapshot.params.id)
      .subscribe(products => {
        // @ts-ignore
        this.products = products;
        console.log(this.products);
      });
  }


  productChange() {

      this.getAllProducts();
      this.getBill();

  }

  getBill() {
    this.billService.getBillByID(this.route.snapshot.params.id)
      .subscribe(bill => {
        // @ts-ignore
        this.bill = bill;
        console.log(this.bill);

      });
  }
  deleteProduct(productId: string) {
    this.billService.deleteProduct(productId)
      .subscribe(d => {
        console.log(d);
        //this.productChange.emit(true);
        this.productChange();
      });

  }
}
