import { NgControl } from '@angular/forms';
import {Directive, Input} from '@angular/core';

@Directive({
  selector: '[disableControl]'
})

// directive for disabling button
export class DisableControlDirective {

  @Input() set disableControl( condition: boolean ) {
    const action = condition ? 'enable' : 'disable';
    this.ngControl.control[action]();
  }

  constructor( private ngControl: NgControl ) {
  }

}
