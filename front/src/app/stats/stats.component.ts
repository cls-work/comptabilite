import {Component, OnInit} from '@angular/core';
import 'echarts/theme/dark.js';
import 'echarts/theme/macarons.js';
import {StatsService} from '../_services/stats.service';

declare const require: any;

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {

  data: any;

  options: any;
  options2: any;

  constructor(private statsService: StatsService) {
  }

  ngOnInit() {

    this.getAllPurchasesByCategories();
  }

  getAllPurchasesByCategories() {
    this.statsService.getAllPurchasesByCategories().subscribe(
      (data) => {
        this.data = data;
        let dataGrouped = this.data.map(function(elt) {
          return {name: elt.category.label, value: elt.purchases.length};
        });
        console.log(dataGrouped);

        let dataGrouped2 = this.data.map(function(elt) {
          return {
            name: elt.category.label, value: elt.purchases.reduce(function(item1, item2) {
              return item1.amountTTC + item2.amountTTC;
            }, 0)
          };
        });

        console.log(dataGrouped2);

        this.options = {
          title: {
            text: 'Nombre d\'articles par catégories produits',
            subtext: 'Pie Chart',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            x: 'center',
            y: 'bottom',
            data: dataGrouped.map(elt => elt.name)
          },
          calculable: true,
          series: [
            {
              name: 'area',
              type: 'pie',
              data: dataGrouped
            }
          ]
        };

        this.options2 = {
          title: {
            text: 'Nombre d\'articles par catégories produits',
            subtext: 'Pie Chart',
            x: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
          },
          legend: {
            x: 'center',
            y: 'bottom',
            data: dataGrouped2.map(elt => elt.name)
          },
          calculable: true,
          series: [
            {
              name: 'area',
              type: 'pie',
              data: dataGrouped2
            }
          ]
        };
      }
    );

  }
}
