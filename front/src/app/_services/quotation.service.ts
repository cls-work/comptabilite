import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, BILLS, DELETEFILE, PRODUCTS ,QUOTATION} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class QuotationService {

  constructor(private http: HttpClient) { }

  getAllQuotations() {
    return this.http.get(BASE_URL + BILLS);
  }
/*
  getProductsByBillId(billId) {
    return this.http.get(BASE_URL + PRODUCTS + billId);

  }*/

  getQuotationByID(id: any) {
    return this.http.get(BASE_URL + QUOTATION + id);
  }

  postQuotation(quotation) {
    return this.http.post(BASE_URL + QUOTATION, quotation);
  }

  deleteQuotation(id) {
    return this.http.delete(BASE_URL + QUOTATION + id);
  }
/*
  editBill(billId: any, value: any) {
    return this.http.put(BASE_URL + BILLS + billId, value);
  }

  postProducts(billId: any, value) {
    return this.http.post(BASE_URL + PRODUCTS + billId, value);
  }

  deleteProduct(productId: string) {
    return this.http.delete(BASE_URL + PRODUCTS + productId);
  }
*/
  deleteFile(fileID : string){
    return this.http.delete(BASE_URL + DELETEFILE + fileID) ;
  }
}
