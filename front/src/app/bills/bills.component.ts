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
  loading: boolean;
  billDeleted: boolean;
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

    if (confirm('Supprimer cette facture')) {
      this.loading = true;
      this.billService.deleteBill(id)
        .subscribe(d => {
          console.log(d);
          this.billDeleted = true;
          this.getAllBills();
          setTimeout(() => {
            this.billDeleted = false;
          }, 3000);
        });
    }
  }


  private getAllBills() {
    this.loading = true;
    this.billService.getAllBills()
      .subscribe(bills => {
        // @ts-ignore
        this.bills = bills;
        console.log(bills);
        this.searchToken = null;
        this.initializeConfig(5, 1, this.bills.length);
        this.loading = false;
      });
  }

  disableArrow(index: number, arrowType: string) {
    const arrows = document.getElementsByClassName('arrow');
    const arrow = document.getElementsByClassName(arrowType)[index];
    // @ts-ignore
    for (const item of arrows) {
      item.classList.remove('hide');
    }
    arrow.classList.add('hide');
  }
}
