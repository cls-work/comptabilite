import { Injectable } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ProductModel} from '../_models/product.model';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  private bills: BillModel[] = [
    {
      id: 'string',
      provider: 'string',
      date: 'string',
      totalHT: 123,
      totalTVA: 123,
      totalTTC: 123,
      taxStamp: 0.6,
      checkReference: 'string',
      checkPayment: true,
    },
    {
      id: 'string1',
      provider: 'string',
      date: 'string',
      totalHT: 123,
      totalTVA: 123,
      totalTTC: 123,
      taxStamp: 0.6,
      checkReference: 'string',
      checkPayment: true
    },
    {
      id: 'string2',
      provider: 'string',
      date: 'string',
      totalHT: 123,
      totalTVA: 123,
      totalTTC: 123,
      taxStamp: 0.6,
      checkPayment: false
    }
  ];

  private products: ProductModel[] = [
    {
      id: 'string',
      designation: 'string',
      quantity: 123,
      unitPrice: 123,
      discount: 123,
      TVA: 123,
      unitPriceAfterDiscount: 123,
      amountHT: 123,
      amountTVA: 123,
      amountTTC: 123,
      idBill: 'string'
    },
    {
      id: 'string',
      designation: 'string',
      quantity: 123,
      unitPrice: 123,
      discount: 123,
      TVA: 123,
      unitPriceAfterDiscount: 123,
      amountHT: 123,
      amountTVA: 123,
      amountTTC: 123,
      idBill: 'string'
    },
    {
      id: 'string',
      designation: 'string',
      quantity: 123,
      unitPrice: 123,
      discount: 123,
      TVA: 123,
      unitPriceAfterDiscount: 123,
      amountHT: 123,
      amountTVA: 123,
      amountTTC: 123,
      idBill: 'string'
    }
  ];

  constructor() { }

  getAllBills(): Promise<BillModel[]> {
    return new Promise<BillModel[]>(resolve => {
      resolve(this.bills);
    });
  }

  getProductsByBillId(id): Promise<ProductModel[]> {
    console.log(id);
    return new Promise<ProductModel[]>(resolve => {
      resolve(this.products);
    });
  }

  getBillByID(id: any) {
    console.log(id);
    return new Promise<BillModel>(resolve => {
      resolve(this.bills[0]);
    });

  }
}
