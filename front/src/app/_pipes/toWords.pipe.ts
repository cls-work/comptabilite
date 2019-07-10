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
    console.log();
    let int = String(_(parseInt(number)));
    let dec = String(_(parseInt(String((number - parseInt(number)) * 1000))));

    if (((number - parseInt(number)) * 100) < 1) {
      return int + ' Dinars';
    }

    if (number < 1) {
      let dec = String(_(parseInt(String((number - parseInt(number)) * 1000))));
      return dec + ' Millimes.';
    }


    console.log(dec);
    return int + ' Dinars, ' + dec + ' Millimes.';
  }
}
