import { Injectable } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ProductModel} from '../_models/product.model';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, BILLS, DELETEFILE, PRODUCTS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class BillService {

  constructor(private http: HttpClient) { }

  getAllBills() {
    return this.http.get(BASE_URL + BILLS);
  }

  getProductsByBillId(billId) {
    return this.http.get(BASE_URL + PRODUCTS + billId);

  }

  getBillByID(billId: any) {
    return this.http.get(BASE_URL + BILLS + billId);
  }

  postBill(bill) {
    return this.http.post(BASE_URL + BILLS, bill);
  }

  deleteBill(billId) {
    return this.http.delete(BASE_URL + BILLS + billId);
  }

  editBill(billId: any, value: any) {
    return this.http.put(BASE_URL + BILLS + billId, value);
  }

  postProducts(billId: any, value) {
    return this.http.post(BASE_URL + PRODUCTS + billId, value);
  }

  deleteProduct(productId: string) {
    return this.http.delete(BASE_URL + PRODUCTS + productId);
  }

  deleteFile(fileID : string){
    return this.http.delete(BASE_URL + DELETEFILE + fileID) ;
  }
}
