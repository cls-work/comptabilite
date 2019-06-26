import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {BillService} from '../_services/bill.service';
import {FileModel} from '../_models/file.model';

@Component({
  selector: 'app-display-file',
  templateUrl: './display-file.component.html',
  styleUrls: ['./display-file.component.scss']
})
export class DisplayFileComponent implements OnInit {

  @Input()   billFiles: FileModel[];
  fileDeleted : boolean ;
  constructor(private billService: BillService) { }

  ngOnInit() {
  }

  deleteFile(file, index ) {
    if (confirm('delete this file?')) {

          this.billService.deleteFile(file.id)
            .subscribe(data => {
              console.log(data);
              this.billFiles.splice(index, 1);
              this.fileDeleted = true;
              setTimeout(() => {
                this.fileDeleted = false;
              }, 3000);
            });
    }
  }

}
