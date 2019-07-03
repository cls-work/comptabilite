import {FileModel} from './file.model';
import {QuotationModel} from './quotation.model';
import {HistoryModel} from './history.model';

export class BillModel {
  id: string;
  creationDate: string;
  checkReference: string;
  checkPayment: boolean;
  quotation: QuotationModel;
  historicals: HistoryModel;
  fileStorageProperties: FileModel[];

}
