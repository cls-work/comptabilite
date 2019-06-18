import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductModel} from '../_models/product.model';
import {BillService} from '../_services/bill.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  @Input() product: ProductModel;
  @Output() productChange = new EventEmitter<boolean>();
  constructor(private billService: BillService) { }

  ngOnInit() {

  }



  deleteProduct(productId: string) {
    this.billService.deleteProduct(productId)
      .subscribe(d => {
        console.log(d);
        this.productChange.emit(true);
    });

  }
}
