import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.scss']
})
export class BillsComponent implements OnInit {
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  /*
    @ViewChild(DataTableDirective, {static: false})
    dtElement: DataTableDirective;

    bills: BillModel[];
    @Input() filteredBills: BillModel[];
    searchToken: string;
    orderByOrder: string;
    orderByColumn: string;
    config: any;
    billDeleted: boolean;
    perPage: number;

    dtTrigger: Subject<HistoryModel[]> = new Subject();

    loading: boolean;
    error: boolean;
    billEdited: boolean;
    selectedBill: BillModel;
    dtOptions: any = {};
    currentLang;

    constructor(private billService: BillService,
                private route: ActivatedRoute,
                private translateService: TranslateService) {
      // this.initializeConfig(0, 0, 0);
      //  this.perPage = 10;
    }

    ngOnInit() {
      if (this.route.snapshot.params.edited === 'edited') {
        this.billEdited = true;
      }


      this.changeDataTableLanguage(localStorage.getItem('lang'));

      this.translateService.langSubject
        .subscribe(lang => {
          this.changeDataTableLanguage(lang);
          console.log('sync');

          this.rerender();
        });

      this.getAllBills();


      // this.orderBy('date', 'desc');
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
  /*
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
      };*/
  /*
      console.log(this.dtOptions);
    }*/


  // edit orderBy vars
  /*orderBy(column: string, order: string) {
    this.orderByOrder = order;
    this.orderByColumn = column;
  }
*/

  // calculate bills[] after searching for an element
  /* billsLengthAfterSearch(): number {
     return this.bills.filter(it => {
       return it.billId.toLowerCase().includes(this.searchToken)
         || it.provider.toLowerCase().includes(this.searchToken)
         || it.checkReference.toString().toLowerCase().includes(this.searchToken)
         || it.date.toLowerCase().includes(this.searchToken);
     }).length;
   }

 */
  // both functions for pagination
  /* initializeConfig(perPage: number, current: number, total: number) {
     this.config = {
       itemsPerPage: perPage,
       currentPage: current,
       totalItems: total
     };
   }

   pageChanged(event) {
     this.config.currentPage = event;
   }*/


  // ------------------------
  // calling service methods (CRUD)

  /*
  deleteBill(index: number) {
    console.log(this.bills[index]);
    if (confirm('Supprimer cette facture')) {
      // this.loading = true;
      this.billService.deleteBill(this.bills[index].billId)
        .subscribe(d => {
          console.log(d);
          this.billDeleted = true;
          //this.getAllBills();
          this.bills.splice(index, 1);
          setTimeout(() => {
            this.billDeleted = false;
          }, 3000);
        });
    }
  }


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
  }*/

  /* disableArrow(index: number, arrowType: string) {
     const arrows = document.getElementsByClassName('arrow');
     const arrow = document.getElementsByClassName(arrowType)[index];
     // @ts-ignore
     for (const item of arrows) {
       item.classList.remove('hide');
     }
     arrow.classList.add('hide');
   }*/
  /*
    onSelectRow(bill: BillModel) {
      this.selectedBill = bill;


    }*/
}
