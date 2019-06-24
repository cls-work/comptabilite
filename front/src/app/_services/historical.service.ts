import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, HISTORICALS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class HistoricalService {


  constructor(private http: HttpClient) {
  }

  getAllHistoricals() {
    return this.http.get(BASE_URL + HISTORICALS);
  }


}
