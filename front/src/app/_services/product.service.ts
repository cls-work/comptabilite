import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProductModel} from '../_models/product.model';
import {Observable} from 'rxjs';
import {BASE_URL, PROVIDERS, PRODUCTS} from '../_globals/vars';

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
