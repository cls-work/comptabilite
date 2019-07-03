import {QuotationModel} from './quotation.model';

export class ProviderModel {
  id: string;
  name: string;
  adresse: string;
  quotations: QuotationModel[];
}
