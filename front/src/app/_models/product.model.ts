import {CategoryModel} from './category.model';

export class ProductModel {
  id: string;
  refrence: string;
  designation: string;
  category: CategoryModel;
}
