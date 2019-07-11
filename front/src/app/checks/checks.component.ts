import { Component, OnInit } from '@angular/core';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';

import {CheckService} from "../_services/check.service";
import {CheckModel} from "../_models/check.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

import {environment} from "../../environments/environment";

@Component({
  selector: 'app-checks',
  templateUrl: './checks.component.html',
  styleUrls: ['./checks.component.scss'],

})
export class ChecksComponent implements OnInit {
  env=environment;
  c: number;
  searchToken: string;



  checks: CheckModel[];

  checkForm: FormGroup;
  loading: boolean;
  error: boolean;

  reference1: any;
  amount1: any;
  transactionDate1: any;
  profilOf1: any;
  profilOf2: any;
  transactionDate2: any;
  amount2: any;
  reference2: any;
  referencetd: any;

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
      profilOf: ['', Validators.required]
      // @ts-ignore


    });
    this.getAllChecks();
  }

  private getAllChecks() {
    this.loading = true;
    this.checkService.getAllChecks()
      .subscribe(checks => {
        // @ts-ignore
        this.checks = checks;

        this.searchToken = null;
        // this.initializeConfig(this.perPage, 1, this.bills.length);
        this.loading = false;
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }


  editCheck() {

  /*  this.reference2=this.collectables[i].reference;
    this.amount2=this.collectables[i].amount;
    this.transactionDate2=this.collectables[i].transactionDate;
    this.profilOf2=this.collectables[i].profilOf;
*/

    this.loading = true;

    const checkId = this.route.snapshot.params.id;
console.log(checkId);
    this.checkService.editCheck(checkId, Object.assign({checkId}, this.checkForm.value))
      .subscribe(() => {
        this.loading = false;
        this.router.navigate(['/checks', 'edited']);
      }, () => {
        this.loading = false;
        this.error = true;
      });


  }

  onAddCheckButton() {
/*
this.collectables.push({
  reference: this.reference1,
  amount:this.amount1,
  transactionDate: this.transactionDate1,
  profilOf: this.profilOf1


});
*/
   this.loading = true;
    // @ts-ignore
    this.checkService.postCheck(this.checkForm.value)
      .subscribe(d => {
        this.loading = false;
        // @ts-ignore
        this.router.navigate(['/checks', d.checkId, 'new']); // to change
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }



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


  onEditCheckButton() {

      this.editCheck();
    }

  }


