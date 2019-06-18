import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.scss']
})
export class BillComponent implements OnInit {

  @Input() bill: BillModel;
  @Output() productChange = new EventEmitter<boolean>();


  constructor(private formBuilder: FormBuilder, private billService: BillService) { }

  ngOnInit() {

  }

  deleteBill(id: string) {
    this.billService.deleteBill(id)
      .subscribe(d => {
        console.log(d);
        this.productChange.emit(true);
      });
  }


  preventDefault($event: MouseEvent) {
    $event.preventDefault();
  }

  checkReferenceAccess() {

  }
}
