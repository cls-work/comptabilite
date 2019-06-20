import {Component, Input, OnInit} from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.scss']
})
export class BillsComponent implements OnInit {

  bills: BillModel[];
  @Input() filteredBills: BillModel[];
  searchToken: string;
  orderByOrder: string;
  orderByColumn: string;
  config: any;
  constructor(private billService: BillService) {
    this.initializeConfig(0, 0, 0);
  }

  ngOnInit() {
    this.getAllBills();
    this.orderBy('date', 'desc');
  }



  // edit orderBy vars
  orderBy(column: string, order: string) {
    this.orderByOrder = order;
    this.orderByColumn = column;
  }


  // calculate bills[] after searching for an element
  billsLengthAfterSearch(): number {
    return this.bills.filter( it => {
      return it.billId.toLowerCase().includes(this.searchToken)
        || it.provider.toLowerCase().includes(this.searchToken)
        || it.checkReference.toString().toLowerCase().includes(this.searchToken)
        || it.date.toLowerCase().includes(this.searchToken);
    }).length;
  }




  // both functions for pagination
  initializeConfig(perPage: number, current: number, total: number) {
    this.config = {
      itemsPerPage: perPage,
      currentPage: current,
      totalItems: total
    };
  }
  pageChanged(event) {
    this.config.currentPage = event;
  }











  // ------------------------
  // calling service methods (CRUD)
  deleteBill(id: string) {
    if(confirm('Supprimer cette facture')) {
      this.billService.deleteBill(id)
        .subscribe(d => {
          console.log(d);
          this.getAllBills();
        });
    }
  }


  private getAllBills() {
    this.billService.getAllBills()
      .subscribe(bills => {
        // @ts-ignore
        this.bills = bills;
        console.log(bills);
        this.searchToken = null;
        this.initializeConfig(5, 1, this.bills.length);
      });
  }
}
