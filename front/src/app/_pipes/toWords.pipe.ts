import {Pipe, PipeTransform} from '@angular/core';
import * as _ from 'written-number';

@Pipe({
  name: 'toWords',
})

// translate data according to lang files
export class ToWordsPipe implements PipeTransform {

  constructor() {
    _.defaults.lang = 'fr';

  }

  transform(number: any): any {
    return _(number);
  }
}
