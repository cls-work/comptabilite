import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, PURCHASES_BY_CATEGORIES} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  constructor(private http: HttpClient) {
  }

  getAllPurchasesByCategories() {
    return this.http.get(BASE_URL + PURCHASES_BY_CATEGORIES);

  }


}
