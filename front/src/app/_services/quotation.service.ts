import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, QUOTATIONS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class QuotationService {

  constructor(private http: HttpClient) {
  }


  postQuotation(quotation) {
    return this.http.post(BASE_URL + QUOTATIONS, quotation);
  }

  findAllQuotations() {
    return this.http.get(BASE_URL + QUOTATIONS);
  }


  findQuotationById(quotationId) {
    return this.http.get(BASE_URL + QUOTATIONS + quotationId);
  }

  deleteQuotationById(quotationId) {
    return this.http.delete(BASE_URL + QUOTATIONS + quotationId);
  }

  confirmQuotationById(quotationId) {
    return this.http.get(BASE_URL + QUOTATIONS + 'confirm/' + quotationId);
  }

  rejectQuotationById(quotationId) {
    return this.http.get(BASE_URL + QUOTATIONS + 'reject/' + quotationId);
  }

}
