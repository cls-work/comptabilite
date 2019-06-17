import {Component, Input, OnInit} from '@angular/core';
import {BillModel} from '../_models/bill.model';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss']
})
export class BillComponent implements OnInit {

  @Input() bill: BillModel;
  constructor() { }

  ngOnInit() {
  }

  deleteBill(id: string) {
    
  }
}
