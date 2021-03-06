import {UserModel} from './user.model';
import {ProviderModel} from './provider.model';
import {PurchaseModel} from './purchase.model';
import {FileModel} from './file.model';

export class QuotationModel {

  id: string;
  creationDate: string;
  totalHT: number;
  totalTTC: number;
  totalTVA: number;
  taxStamp: number;
  confirmed: boolean;
  isChecked: boolean;

  createdBy: UserModel;
  acceptedBy: UserModel;
  provider: ProviderModel;
  purchases: PurchaseModel[];
  hasBill: boolean;
  fileStorageProperties: FileModel[];
}
