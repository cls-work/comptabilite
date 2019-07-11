import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, CATEGORY} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  constructor(private http: HttpClient) {
  }

  getAllCategories() {
    return this.http.get(BASE_URL + CATEGORY);
  }
}
