import { Pipe, PipeTransform } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {HistoryModel} from '../_models/history.model';
@Pipe({
  name: 'filterHistory'
})

// filter bill array depending on id, billId, user name, message and date
export class FilterHistoryPipe implements PipeTransform {
  transform(items: HistoryModel[], searchText: string): HistoryModel[] {
    if (!items) { return []; }
    if (!searchText) { return items; }
    searchText = searchText.toLowerCase();
    return items.filter( it => {
      return it.id.toString().toLowerCase().includes(searchText)
        || it.bill.billId.toLowerCase().includes(searchText)
        || it.user.name.toString().toLowerCase().includes(searchText)
        || it.message.toString().toLowerCase().includes(searchText)
        || it.date.toLowerCase().includes(searchText);
    });
  }
}
