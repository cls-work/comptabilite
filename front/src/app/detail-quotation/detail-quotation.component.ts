import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import * as jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import {ProductModel} from '../_models/product.model';
import {CategoryModel} from '../_models/category.model';
import {PurchaseModel} from '../_models/purchase.model';
import {ProviderModel} from '../_models/provider.model';
import {QuotationModel} from '../_models/quotation.model';
import {QuotationService} from '../_services/quotation.service';
import {ActivatedRoute} from '@angular/router';

declare let $: any;

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
  quotation: QuotationModel;
  private totalInWords: string;

  constructor(private quotationService: QuotationService,
              private route: ActivatedRoute) {

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

    this.getQuotationById(this.route.snapshot.params['quotationId']);
    /* from(this.quotation.purchases).pipe(
       groupBy(purchase => purchase.amountTVA),
       // return each item in group as array
       mergeMap(elt => zip(of(elt.key), elt.pipe(toArray())))
     ).subscribe(console.log);
     */


    this.groupedByTVA = this.groupByM(this.quotation.purchases, item => item.amountTVA);
    console.log(this.groupedByTVA);


  }

  print() {
    /* var element = document.getElementById('container');
     const opt = {
       margin: 0,
       filename: 'filename',
       image: {type: 'jpeg', quality: 1},
       html2canvas: {
         scale: 3,
         letterRendering: true,
         useCORS: true
       },
       jsPDF: {unit: 'mm', format: 'a4', orientation: 'portrait', pagesplit: true}
     };
     const worker = html2pdf().from(element).set(opt).save()
       .then(done => {
         console.log(worker);
       });*/


    html2canvas(document.querySelector('#canvas'), {}).then(canvas => {


      var w = document.getElementById('canvas').offsetWidth;
      var h = document.getElementById('canvas').offsetHeight;
      console.log(' w ' + w + ' h ' + h);
      var dataURL = canvas.toDataURL('image/png', 1);
      var pdf = new jsPDF('p', 'mm');

      let factor = w / 210;


      pdf.addImage(dataURL, 'PNG', 0, 0, 210, h / factor); //addImage(image, format, x-coordinate, y-coordinate, width, height)
      pdf.save('CanvasJS Charts.pdf');
    });
  }


  getQuotationById(id: string) {
    this.quotationService.findQuotationById(id).subscribe(
      (data: any) => {
        this.quotation = data;
        console.log(this.quotation);
      }, (error) => {

      }
    );
  }

}

