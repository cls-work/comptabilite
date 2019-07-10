import {ProviderModel} from './provider.model';
import {PurchaseModel} from './purchase.model';

export class QuotationModel {

  id: string;
  creationDate: string;
  totalHT: number;
  totalTTC: number;
  totalTVA: number;
  taxStamp: number;
  isConfirmed: boolean;
  isChecked: boolean;
//  bill: BillModel;

  //createdBy: UserModel;
  //acceptedBy: UserModel;
  provider: ProviderModel;
  purchases: PurchaseModel[];
}
