import { Injectable } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ProductModel} from '../_models/product.model';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, BILLS, PRODUCTS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class BillService {
/*
  private bills: BillModel[] = [
    {
      billId: 'string',
      provider: 'string',
      date: '2012-12-13',
      totalHT: 123,
      totalTVA: 123,
      totalTTC: 123,
      taxStamp: 0.6,
      checkReference: 'string',
      checkPayment: 1,
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
      checkPayment: 1
    },
    {
      id: 'string2',
      provider: 'string',
      date: 'string',
      totalHT: 123,
      totalTVA: 123,
      totalTTC: 123,
      taxStamp: 0.6,
      checkPayment: 0
    }
  ];*/

 /* private products: ProductModel[] = [
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
  ];*/

  constructor(private http: HttpClient) { }

  getAllBills() {
    return this.http.get(BASE_URL + BILLS);
  }

  getProductsByBillId(id) {
    console.log(id);
    return this.http.get(BASE_URL + PRODUCTS + id);

  }

  getBillByID(id: any) {
    return this.http.get(BASE_URL + BILLS + id);
  }

  postBill(bill) {
    return this.http.post(BASE_URL + BILLS, bill);
  }

  deleteBill(billId) {
    return this.http.delete(BASE_URL + BILLS + billId);
  }

  editBill(billId: any, value: any) {
    console.log(value);
    return this.http.put(BASE_URL + BILLS + billId, value);
  }

  postProducts(billId: any, value) {
    console.log(value);
    return this.http.post(BASE_URL + PRODUCTS + billId, value);
  }

  deleteProduct(productId: string) {
    return this.http.delete(BASE_URL + PRODUCTS + productId);
  }
}
