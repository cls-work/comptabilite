import {Component, OnInit, Provider} from '@angular/core';
import {QuotationModel} from '../_models/quotation.model';
import {UserModel} from '../_models/user.model';
import {Subject} from 'rxjs';
import {ProviderModel} from '../_models/provider.model';
import {providerDef} from '@angular/compiler/src/view_compiler/provider_compiler';

@Component({
  selector: 'app-list-quotation',
  templateUrl: './list-quotation.component.html',
  styleUrls: ['./list-quotation.component.scss']
})
export class ListQuotationComponent implements OnInit {
  dtTrigger: Subject<QuotationModel[]> = new Subject();
  dtOptions: any = {};
  user: UserModel;

  provider: ProviderModel;
  quotations: QuotationModel[];


  constructor() {
  }

  ngOnInit() {
    this.user = {
      accessToken: '', email: '', id: '', lang: '', name: '', role: '', username: ''

    };
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

    this.quotations = [{
      acceptedBy: this.user,
      bill: undefined,
      createdBy: this.user,
      creattionDate: '15/04/2019',
      id: '1',
      isChecked: false,
      isConfirmed: true,
      provider: this.provider,
      purchases: [],
      taxStamp: 0,
      totalHT: 0,
      totalTTC: 0,
      totalTVA: 0
    }, {
      acceptedBy: undefined,
      bill: undefined,
      createdBy: undefined,
      creattionDate: '16/04/2019',
      id: '2',
      isChecked: false,
      isConfirmed: false,
      provider: this.provider,
      purchases: [],
      taxStamp: 55,
      totalHT: 30,
      totalTTC: 20,
      totalTVA: 200
    }, {
      acceptedBy: undefined,
      bill: undefined,
      createdBy: undefined,
      creattionDate: '17/04/2019',
      id: '3',
      isChecked: false,
      isConfirmed: undefined,
      provider: this.provider,
      purchases: [],
      taxStamp: 0,
      totalHT: 0,
      totalTTC: 0,
      totalTVA: 0
    }];
    this.dtTrigger.next();
  }


}
