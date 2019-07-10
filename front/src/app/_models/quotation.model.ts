import {BillModel} from './bill.model';
import {UserModel} from './user.model';
import {ProviderModel} from './provider.model';
import {PurchaseModel} from './purchase.model';
import {FileModel} from './file.model';

export class QuotationModel {

  id: string;
  creattionDate: string;
  totalHT: number;
  totalTTC: number;
  totalTVA: number;
  taxStamp: number;
  isConfirmed: boolean;
  isChecked: boolean;
  fileStorageProperties: FileModel[];

  createdBy: UserModel;
  acceptedBy: UserModel;
  provider: ProviderModel;
  purchases: PurchaseModel[];
}
