import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import * as jsPDF from 'jspdf';
import {ProductModel} from '../_models/product.model';
import {CategoryModel} from '../_models/category.model';
import {PurchaseModel} from '../_models/purchase.model';
import {ProviderModel} from '../_models/provider.model';
import {QuotationModel} from '../_models/quotation.model';


@Component({
  selector: 'app-detail-quotation',
  templateUrl: './detail-quotation.component.html',
  styleUrls: ['./detail-quotation.component.scss']
})
export class DetailQuotationComponent implements OnInit {

  // @ts-ignore
  @ViewChild('content') content: ElementRef;
  category_list: CategoryModel[] = [
    {id: 1, label: 'categorie 1'},
    {id: 2, label: 'categorie 2'},
    {id: 3, label: 'categorie 3'},
    {id: 4, label: 'categorie 4'},
  ];
  produit_list: ProductModel[] = [
    {category: this.category_list[0], designation: 'Casque pc gamer bluetooth 5.1', id: '1', refrence: 'REF01'},
    {category: this.category_list[1], designation: 'pc gamer bluetooth INTEL', id: '2', refrence: 'REF02'},
    {category: this.category_list[2], designation: 'souris gamer bluetooth ', id: '3', refrence: 'REF03'},
    {category: this.category_list[2], designation: 'souris hp', id: '4', refrence: 'REF04'},
  ];
  purchase_list: PurchaseModel[] = [
    {
      TVA: 7,
      amountHT: 102,
      amountTTC: 30,
      amountTVA: 7,
      discount: 5,
      product: this.produit_list[0],
      quantity: 10,
      unitPrice: 10.5,
      unitPriceAfterDiscount: 10.2
    }, {
      TVA: 13,
      amountHT: 102,
      amountTTC: 30,
      amountTVA: 13,
      discount: 5,
      product: this.produit_list[1],
      quantity: 10,
      unitPrice: 10.5,
      unitPriceAfterDiscount: 10.2
    }, {
      TVA: 19,
      amountHT: 102,
      amountTTC: 30,
      amountTVA: 19,
      discount: 5,
      product: this.produit_list[2],
      quantity: 10,
      unitPrice: 10.5,
      unitPriceAfterDiscount: 10.2
    }, {
      TVA: 19,
      amountHT: 102,
      amountTTC: 30,
      amountTVA: 19,
      discount: 5,
      product: this.produit_list[3],
      quantity: 10,
      unitPrice: 10.5,
      unitPriceAfterDiscount: 10.2
    },
  ];
  provider: ProviderModel = {adresse: 'CHARGUIA adresse x21a321313', id: '1', name: 'fournisseur xyz'};
  groupedByTVA: any[];
  quotation: QuotationModel = {

    creattionDate: '10/01/2019',
    id: '1',
    isChecked: false,
    isConfirmed: false,
    provider: this.provider,
    purchases: this.purchase_list,
    taxStamp: 0.600,
    totalHT: 5100,
    totalTTC: 54040654.851,
    totalTVA: 300.2
  };
  private totalInWords: string;

  constructor() {

  }

  public groupByM(array, f) {
    let groups = {};
    array.forEach(function(o) {
      var group = JSON.stringify(f(o));
      groups[group] = groups[group] || [];
      groups[group].push(o);
    });
    return Object.keys(groups).map(function(group) {
      return groups[group];
    });
  }

  ngOnInit(): void {


    /* from(this.quotation.purchases).pipe(
       groupBy(purchase => purchase.amountTVA),
       // return each item in group as array
       mergeMap(elt => zip(of(elt.key), elt.pipe(toArray())))
     ).subscribe(console.log);
     */


    this.groupedByTVA = this.groupByM(this.quotation.purchases, item => item.amountTVA);
    console.log(this.groupedByTVA);

  }


  public printt() {
    let doc = new jsPDF();

    let content = this.content.nativeElement;
    doc.fromHTML(content.innerHTML, 0, 0, {
      'width': 1108,
    });

    doc.save('my.pdf');

  }

  calculateTotals() {


  }


}
