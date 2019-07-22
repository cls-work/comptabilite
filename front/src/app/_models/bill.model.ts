import {QuotationModel} from './quotation.model';

export class BillModel {
  id: string;
  creationDate: Date;
  checkReference: string;
  checkPayment: boolean;
  quotation: QuotationModel;

}

