import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, PRODUCTS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {
  }

  getAllProducts() {
    return this.http.get(BASE_URL + PRODUCTS);

  }

  postProduct(product) {
    console.log(product);
    return this.http.post(BASE_URL + PRODUCTS, product);
  }


}
