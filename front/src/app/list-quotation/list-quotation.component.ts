import {Component, OnInit} from '@angular/core';
import {QuotationModel} from '../_models/quotation.model';
import {Subject} from 'rxjs';
import {ProviderModel} from '../_models/provider.model';
import {QuotationService} from '../_services/quotation.service';

@Component({
  selector: 'app-list-quotation',
  templateUrl: './list-quotation.component.html',
  styleUrls: ['./list-quotation.component.scss']
})
export class ListQuotationComponent implements OnInit {
  dtTrigger: Subject<QuotationModel[]> = new Subject();
  dtOptions: any = {};


  provider: ProviderModel;
  quotations: QuotationModel[];


  constructor(private quotationService: QuotationService) {
  }

  ngOnInit() {
    this.getAllQuotations();


    this.provider = {adresse: 'adresse', id: '1', name: 'provider 1'};
    this.dtOptions = {
      pagingType: 'full_numbers',
      colReorder: {},
      dom: 'Bfrtip',
      select: true,

      buttons: [
        'colvis',
        {
          extend: 'copyHtml5',
          exportOptions: {
            columns: [0, ':visible']
          }
        },
        {
          extend: 'excelHtml5',
          exportOptions: {
            columns: ':visible'
          }
        },
        {
          extend: 'pdfHtml5',
          exportOptions: {
            columns: ':visible'
          }
        },

      ]
    };


  }

  getAllQuotations() {
    this.quotationService.findAllQuotations().subscribe(
      (data: any) => {
        this.quotations = data;
        console.log(this.quotations);
        this.dtTrigger.next();
      }, (error) => {

      }
    );
  }

  onDeleteQuotation(index: number) {

    if (confirm('Supprimer ce devis ?')) {

      this.quotationService.deleteQuotationById(this.quotations[index].id).subscribe(
        (data) => {
          this.quotations.splice(index, 1);
        }, () => {

        }
      );

    }

  }


}
