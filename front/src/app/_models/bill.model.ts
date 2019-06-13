import {ProductModel} from './product.model';

export class BillModel {
  id: string;
  provider: string;
  date: string;
  totalHT: number;
  totalTVA: number;
  totalTTC: number;
  taxStamp: number;
  checkReference?: string;
  checkPayment: boolean;
}
