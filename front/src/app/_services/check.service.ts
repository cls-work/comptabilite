import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {BASE_URL, BILLS, CHECKS} from "../_globals/vars";
import {CheckModel} from "../_models/check.model";

@Injectable({
  providedIn: 'root'
})

export class CheckService {
  private collection: CheckModel[] = [];

  constructor(private http: HttpClient) {
  }
  getAllChecks() {
    return this.http.get(BASE_URL + CHECKS);
  }
  getCheckById(checkId: any) {
    return this.http.get(BASE_URL + CHECKS + checkId);
  }
  deleteCheck(checkId) {
    return this.http.delete(BASE_URL + CHECKS + checkId);
  }
  editCheck(checkId: any, value: any) {
    return this.http.put(BASE_URL + CHECKS + checkId, value);
  }
  postCheck(check) {
    return this.http.post(BASE_URL + BILLS, check);
  }

  onAddCheckButton(item: CheckModel) {
this.collection.push(item);
  }
}
