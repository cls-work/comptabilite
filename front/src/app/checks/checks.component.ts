import { Component, OnInit } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';

import {CheckService} from "../_services/check.service";
import {CheckModel} from "../_models/check.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-checks',
  templateUrl: './checks.component.html',
  styleUrls: ['./checks.component.scss']
})
export class ChecksComponent implements OnInit {
  coll = [
    { reference : '69', amount: '20', transactionDate: '20', profilOf: 'hello', stat: 'done'

    }   ,
    { reference : '69', amount: '20', transactionDate: '20', profilOf: 'hello', stat: 'done'

    }

  ];

  checks: CheckModel[];

  checkForm: FormGroup;
  loading: boolean;
  error: boolean;
  private collection: CheckModel [] = [];

  constructor(private formBuilder: FormBuilder,
              private checkService: CheckService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  /*onAddCheckButton() {

    this.checkService.postCheck(this.checkForm.value)
    // bill will be sended automatically when files are uploaded

  } */


  ngOnInit() {
    this.loading = true;

    const id = this.route.snapshot.params.id;
    this.checkForm = this.formBuilder.group({

      reference: ['', Validators.required],
      amount: ['', Validators.required],
      transactionDate: ['', Validators.required],
      profilOf: ['', Validators.required],
      // @ts-ignore


    });  }

  editCheck() {
    this.loading = true;

    const checkId = this.route.snapshot.params.id;




    this.checkService.editCheck(checkId, Object.assign({checkId}, this.checkForm.value))
      .subscribe(() => {
        this.loading = false;
        this.router.navigate(['/checks', 'edited']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }

  onAddCheckButton(item: CheckModel) {
    this.checkService.onAddCheckButton(item);




/*    this.loading = true;

    // @ts-ignore
    this.checkService.postCheck(this.checkForm.value)
      .subscribe(d => {
        this.loading = false;
        // @ts-ignore
        this.router.navigate(['/checks', d.billId, 'new']); // to change
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }

*/

  /*  if (id) {

      this.checkService.getCheckById(this.route.snapshot.params.id)
        .subscribe(checks => {
          // @ts-ignore
          this.checks = checks;

          // @ts-ignore
          this.checkForm = this.formBuilder.group({
            reference: [this.checks.reference, Validators.required],
            amount: [this.checks.amount, Validators.required],
            TransactionDate: [this.checks.transactionDate, Validators.required],
            profilOf: [this.checks.profilOf, Validators.required],

            // @ts-ignore

          }, () => {
            this.error = true;
            this.loading = false;
          });
          this.loading = false;
        });
    } else {
      this.loading = false;


    }
*/

    /* editCheck() {
       this.loading = true;

       const checkId = this.route.snapshot.params.id;



       this.checkService.editCheck(checkId, Object.assign({checkId}, this.checkForm.value))
         .subscribe(() => {
           this.loading = false;
           this.router.navigate(['/check', 'edited']);
         }, () => {
           this.loading = false;
           this.error = true;
         });
     } */

    /*onEditCheckButton() {

        this.loading = true;

        const checkId = this.route.snapshot.params.id;


        this.checkService.editCheck(checkId, Object.assign({checkId}, this.checkForm.value))
          .subscribe(() => {
            this.loading = false;
            this.router.navigate(['/check', 'edited']);
          }, () => {
            this.loading = false;
            this.error = true;
          });
      }*/


}}
