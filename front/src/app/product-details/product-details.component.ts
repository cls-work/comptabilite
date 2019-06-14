import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from '../_models/product.model';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.scss']
})
export class ProductDetailsComponent implements OnInit {

  @Input() product: ProductModel;
  constructor() { }

  ngOnInit() {

  }

  preventDefault($event: MouseEvent) {
    $event.preventDefault();
  }

  deleteProduct(id: string) {
    
  }
}
