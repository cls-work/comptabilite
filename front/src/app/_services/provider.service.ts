import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BASE_URL, PROVIDERS} from '../_globals/vars';

@Injectable({
  providedIn: 'root'
})
export class ProviderService {

  constructor(private http: HttpClient) {
  }

  getAllProviders() {
    return this.http.get(BASE_URL + PROVIDERS);
  }

  postProvider(provider) {
    return this.http.post(BASE_URL + PROVIDERS, provider);
  }

}
