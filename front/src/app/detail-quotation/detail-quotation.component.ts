import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import * as jsPDF from 'jspdf';
import html2canvas from 'html2canvas';
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
        this.groupedByTVA = this.groupByM(this.quotation.purchases, item => item.tva);
        console.log(this.groupedByTVA);

      }, (error) => {

      }
    );
  }

}

