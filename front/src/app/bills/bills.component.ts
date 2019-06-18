import { Component, OnInit } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.scss']
})
export class BillsComponent implements OnInit {

  bills: BillModel[];
  constructor(private billService: BillService) { }

  ngOnInit() {
    this.getAllProducts();
  }

  productChange($event: boolean) {
    if ($event) {
      this.getAllProducts();
    }
  }

  private getAllProducts() {

    this.billService.getAllBills()
      .subscribe(bills => {
        // @ts-ignore
        this.bills = bills;
        console.log(bills);
      });
  }
}
