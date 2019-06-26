import {Component, OnInit} from '@angular/core';
import {HistoricalService} from '../_services/historical.service';
import {HistoryModel} from '../_models/history.model';

@Component({
  selector: 'app-historicals-list',
  templateUrl: './historicals-list.component.html',
  styleUrls: ['./historicals-list.component.scss']
})
export class HistoricalsListComponent implements OnInit {

  historicals: HistoryModel[];
  searchToken: string;
  orderByOrder: string;
  orderByColumn: string;
  config: any;
  loading: boolean;
  perPage: number;

  constructor(private historicalsService: HistoricalService) {
      this.initializeConfig(0, 0, 0);
      this.perPage = 5;
}

  ngOnInit() {
    this.getHistoricals();
    this.orderBy('date', 'desc');

}

  getHistoricals() {
    this.loading = true;
    this.historicalsService.getAllHistoricals()
      .subscribe((data: any) => {
          this.historicals = data;
          console.log(data);
          this.searchToken = null;
          this.initializeConfig(this.perPage, 1, this.historicals.length);
          this.loading = false;
        }
      );
  }


  pageChanged(event) {
    this.config.currentPage = event;
  }

  // edit orderBy vars
  orderBy(column: string, order: string) {
    this.orderByOrder = order;
    this.orderByColumn = column;
  }


  // calculate bills[] after searching for an element
  historyLengthAfterSearch(): number {
    return this.historicals.filter( it => {
      return it.id.toString().toLowerCase().includes(this.searchToken)
        || it.bill.billId.toLowerCase().includes(this.searchToken)
        || it.user.name.toString().toLowerCase().includes(this.searchToken)
        || it.message.toString().toLowerCase().includes(this.searchToken)
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
