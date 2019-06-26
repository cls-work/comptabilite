
import { Pipe, PipeTransform } from '@angular/core';
import * as _ from 'lodash';
import {BillModel} from '../_models/bill.model';

@Pipe({ name: 'orderBy' })
export class OrderByPipe implements PipeTransform {

  transform(value: any[], column: string, order = ''): any[] {
    if (!value || !column || column === '' || order === '') { return value; } // no array
    if (value.length <= 1) { return value; } // array with only one item
    console.log( _.orderBy(value, [column], [order]));
    return _.orderBy(value, [column], [order]);
  }
}
