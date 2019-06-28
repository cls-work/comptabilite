import {Component, OnInit} from '@angular/core';
import {HistoricalService} from '../_services/historical.service';
import {HistoryModel} from '../_models/history.model';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-historicals-list',
  templateUrl: './historicals-list.component.html',
  styleUrls: ['./historicals-list.component.scss']
})
export class HistoricalsListComponent implements OnInit {

  historicals: HistoryModel[];
  searchToken: string;
  orderByOrder: string;
  orderByColumn: string;
  config: any;
  loading: boolean;
  perPage: number;
  dtTrigger: Subject<HistoryModel[]> = new Subject();

  dtOptions: any = {};


  constructor(private historicalsService: HistoricalService) {
    this.initializeConfig(0, 0, 0);
    this.perPage = 5;
  }

  ngOnInit() {
    this.getHistoricals();
    this.orderBy('date', 'desc');

    this.dtOptions = {
      pagingType: 'full_numbers',
      colReorder: {},
      dom: 'Bfrtip',
      language: {
        'sProcessing': 'Traitement en cours...',
        'sSearch': 'Rechercher&nbsp;:',
        'sLengthMenu': 'Afficher _MENU_ &eacute;l&eacute;ments',
        'sInfo': 'Affichage de l\'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments',
        'sInfoEmpty': 'Affichage de l\'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment',
        'sInfoFiltered': '(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)',
        'sInfoPostFix': '',
        'sLoadingRecords': 'Chargement en cours...',
        'sZeroRecords': 'Aucun &eacute;l&eacute;ment &agrave; afficher',
        'sEmptyTable': 'Aucune donn&eacute;e disponible dans le tableau',
        'oPaginate': {
          'sFirst': 'Premier',
          'sPrevious': 'Pr&eacute;c&eacute;dent',
          'sNext': 'Suivant',
          'sLast': 'Dernier'
        },
        'oAria': {
          'sSortAscending': ': activer pour trier la colonne par ordre croissant',
          'sSortDescending': ': activer pour trier la colonne par ordre d&eacute;croissant'
        },
        'select': {
          'rows': {
            _: '%d lignes séléctionnées',
            0: 'Aucune ligne séléctionnée',
            1: '1 ligne séléctionnée'
          }
        }
      },
      buttons: [
        'colvis',
        'copy',
        'print',
        'excel',
        'pdf'
      ]
    };
  }

  getHistoricals() {
    this.loading = true;
    this.historicalsService.getAllHistoricals()
      .subscribe((data: any) => {
          this.historicals = data;

          this.searchToken = null;
          this.initializeConfig(this.perPage, 1, this.historicals.length);
          this.loading = false;
          this.dtTrigger.next();

        }
      );
  }


  pageChanged(event) {
    this.config.currentPage = event;
  }

  // edit orderBy vars
  orderBy(column: string, order: string) {
    this.orderByOrder = order;
    this.orderByColumn = column;
  }


  // calculate bills[] after searching for an element
  historyLengthAfterSearch(): number {
    return this.historicals.filter(it => {
      return it.id.toString().toLowerCase().includes(this.searchToken)
        || it.bill.billId.toLowerCase().includes(this.searchToken)
        || it.user.name.toString().toLowerCase().includes(this.searchToken)
        || it.message.toString().toLowerCase().includes(this.searchToken)
        || it.date.toLowerCase().includes(this.searchToken);
    }).length;
  }

  // both functions for pagination
  initializeConfig(perPage: number, current: number, total: number) {
    this.config = {
      itemsPerPage: perPage,
      currentPage: current,
      totalItems: total
    };
  }


  disableArrow(index: number, arrowType: string) {
    const arrows = document.getElementsByClassName('arrow');
    const arrow = document.getElementsByClassName(arrowType)[index];
    // @ts-ignore
    for (const item of arrows) {
      item.classList.remove('hide');
    }
    arrow.classList.add('hide');
  }

}
