import {QuotationModel} from './quotation.model';
import {ProductModel} from './product.model';

export class PurchaseModel {
  id: string;
  quantity: number;
  unitPrice: number;
  discount: number;
  TVA: number;
  unitPriceAfterDiscount: number;
  amountHT: number;
  amountTVA: number;
  amountTTC: number;
  quotation: QuotationModel;
  product: ProductModel;
}
