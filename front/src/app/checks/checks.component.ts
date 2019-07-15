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
  env = environment;
  c: number;
  searchToken: string;
  checkEdited: boolean;


  checks: CheckModel[];

  checkForm: FormGroup;
  loading: boolean;
  error: boolean;

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
    if (this.route.snapshot.params.edited === 'edited') {
      this.checkEdited = true;
    }
    this.getAllChecks();
  }

  private getAllChecks()
    {
      this.loading = true;
      this.checkService.getAllChecks()
        .subscribe(d => {
          // @ts-ignore
          this.checks = d;

          this.searchToken = null;
          // this.initializeConfig(this.perPage, 1, this.bills.length);
          this.loading = false;
        }, () => {
          this.loading = false;
          this.error = true;
        });
    }

  }


