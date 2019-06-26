import {BillModel} from './bill.model';
import {UserModel} from './user.model';

export class HistoryModel {
  id: number;
  bill: BillModel;
  message: string;
  date: string;
  user: UserModel;
}
