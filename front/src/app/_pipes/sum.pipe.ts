import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'sum'
})
export class SumPipe implements PipeTransform {
  transform(items: any[], attr: string): any {
    return items.reduce((a, b) => a + b[attr], 0);
  }
}

@Pipe({
  name: 'sum_tva'
})

export class SumTva implements PipeTransform {
  transform(items: any[], attr: string): any {
    return items.reduce((a, b) => a + b[attr] * b['TVA'] / 100, 0);
  }
}
