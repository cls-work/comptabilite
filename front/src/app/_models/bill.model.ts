import {FileModel} from './file.model';

export class BillModel {
  billId: string;
  provider: string;
  date: string;
  totalHT: number;
  totalTVA: number;
  totalTTC: number;
  taxStamp: number;
  checkReference?: string;
  checkPayment: number; // 1 if payment method is check, 0 otherwise
  fileStorageProperties: FileModel[];
}
