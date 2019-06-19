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
  searchToken: string;
  constructor(private billService: BillService) { }

  ngOnInit() {
    this.getAllBills();
  }

  productChange($event: boolean) {
    if ($event) {
      this.getAllBills();
    }
  }

  private getAllBills() {

    this.billService.getAllBills()
      .subscribe(bills => {
        // @ts-ignore
        this.bills = bills;
        console.log(bills);
      });
  }



  deleteBill(id: string) {
    this.billService.deleteBill(id)
      .subscribe(d => {
        console.log(d);
        this.getAllBills();
      });
  }
}
