import {Component, OnInit, ViewChild} from '@angular/core';
import {QuotationModel} from '../_models/quotation.model';
import {Subject} from 'rxjs';
import {ProviderModel} from '../_models/provider.model';
import {QuotationService} from '../_services/quotation.service';
import {TranslateService} from '../_services/translate.service';
import {DataTableDirective} from 'angular-datatables';
import {DATATABLE_LANG_DE, DATATABLE_LANG_EN, DATATABLE_LANG_FR} from '../_globals/vars';

@Component({
  selector: 'app-list-quotation',
  templateUrl: './list-quotation.component.html',
  styleUrls: ['./list-quotation.component.scss']
})
export class ListQuotationComponent implements OnInit {

  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;

  dtTrigger: Subject<QuotationModel[]> = new Subject();
  dtOptions: any = {};


  provider: ProviderModel;
  quotations: QuotationModel[];
  currentLang;


  constructor(private quotationService: QuotationService,
              private translateService: TranslateService) {
  }

  ngOnInit() {

    this.changeDataTableLanguage(localStorage.getItem('lang'));


    this.translateService.langSubject
      .subscribe(lang => {
        this.changeDataTableLanguage(lang);
        console.log('sync');

        this.rerender();
      });
    this.getAllQuotations();



    this.provider = {adresse: 'adresse', id: '1', name: 'provider 1'};



  }


  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
    console.log('rerender');
  }

  changeDataTableLanguage(lang) {

    console.log(lang);
    this.currentLang = lang;
    let language;
    if (lang === 'fr') {
      language = DATATABLE_LANG_FR;
      console.log('change fr');
    } else if (lang === 'en') {
      console.log('change en');
      language = DATATABLE_LANG_EN;
    } else if (lang === 'de') {
      console.log('change de');
      language = DATATABLE_LANG_DE;
    }

    console.log(language);

    this.setDtOptions(language);
  }

  setDtOptions(lang) {
    this.dtOptions = {
      pagingType: 'full_numbers',
      colReorder: {},
      dom: 'Bfrtip',
      select: true,

      /* language: {
         url:'/assets/i18n/datatable-french.json'
       },*/

      language: lang,
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
