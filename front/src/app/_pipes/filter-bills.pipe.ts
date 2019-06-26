import { Pipe, PipeTransform } from '@angular/core';
import {BillModel} from '../_models/bill.model';
@Pipe({
  name: 'filterBills'
})
export class FilterBillsPipe implements PipeTransform {
  transform(items: BillModel[], searchText: string): BillModel[] {
    if (!items) { return []; }
    if (!searchText) { return items; }
    searchText = searchText.toLowerCase();
    return items.filter( it => {
      return it.billId.toLowerCase().includes(searchText)
        || it.provider.toLowerCase().includes(searchText)
        || it.checkReference.toString().toLowerCase().includes(searchText)
        || it.date.toLowerCase().includes(searchText);
    });
  }
}
