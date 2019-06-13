import {Component, Input, OnInit} from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ProductModel} from '../_models/product.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute} from '@angular/router';

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

    this.billService.getBillByID(this.route.snapshot.params.id)
      .then(bill => {
        this.bill = bill;
        console.log(this.bill);
      });

    this.billService.getProductsByBillId(this.route.snapshot.params.id)
      .then(products => {
        this.products = products;
        console.log(this.products);
      });


  }

  deleteProduct(id: string) {
    console.log('effacer ', id);
  }
}
