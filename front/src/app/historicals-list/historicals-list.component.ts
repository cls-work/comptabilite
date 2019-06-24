import {Component, OnInit} from '@angular/core';
import {HistoricalService} from '../_services/historical.service';

@Component({
  selector: 'app-historicals-list',
  templateUrl: './historicals-list.component.html',
  styleUrls: ['./historicals-list.component.scss']
})
export class HistoricalsListComponent implements OnInit {

  historicals: any[];

  constructor(private historicalsService: HistoricalService) {
  }

  ngOnInit() {
    this.getHistoricals();
  }

  getHistoricals() {
    this.historicalsService.getAllHistoricals().subscribe((data: any) => this.historicals = data);
  }

}
