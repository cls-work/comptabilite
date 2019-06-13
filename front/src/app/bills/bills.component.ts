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
    this.billService.getAllBills()
      .then(bills => {
        this.bills = bills;
        console.log(this.bills);
      });
  }

  deleteBill(id: string) {
    console.log('bill to delete ', id);
  }
}
