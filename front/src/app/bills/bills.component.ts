import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {DataTableDirective} from 'angular-datatables';
import {BillModel} from '../_models/bill.model';
import {Subject} from 'rxjs';
import {HistoryModel} from '../_models/history.model';
import {BillService} from '../_services/bill.service';
import {ActivatedRoute} from '@angular/router';
import {TranslateService} from '../_services/translate.service';
import {DATATABLE_LANG_DE, DATATABLE_LANG_EN, DATATABLE_LANG_FR} from '../_globals/vars';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.scss']
})
export class BillsComponent implements OnInit {


  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;

  bills: BillModel[];
  @Input() filteredBills: BillModel[];
  searchToken;
  config: any;


  dtTrigger: Subject<HistoryModel[]> = new Subject();

  loading: boolean;
  error: boolean;
  dtOptions: any = {};
  currentLang;

  constructor(private billService: BillService,
              private route: ActivatedRoute,
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

    this.getAllBills();


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

  /*
      console.log(this.dtOptions);
    }*/


  private getAllBills() {
    this.loading = true;
    this.billService.getAllBills()
      .subscribe(bills => {
        // @ts-ignore
        this.bills = bills;
        this.dtTrigger.next();
        this.searchToken = null;
        // this.initializeConfig(this.perPage, 1, this.bills.length);
        this.loading = false;
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }


}
