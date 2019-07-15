import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BillModel} from "../_models/bill.model";
import {BillService} from "../_services/bill.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BASE_URL} from "../_globals/vars";
import {CheckModel} from "../_models/check.model";
import {CheckService} from "../_services/check.service";

@Component({
  selector: 'app-check-form',
  templateUrl: './check-form.component.html',
  styleUrls: ['./check-form.component.scss']
})
export class CheckFormComponent implements OnInit {



  checkForm: FormGroup;
  isCheckPayment = false;
  check: CheckModel;

  loading: boolean;
  error: boolean;
  submitted: boolean;



  constructor(private formBuilder: FormBuilder,
              private checkService: CheckService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    const id = this.route.snapshot.params.id;


    this.checkForm = this.formBuilder.group({
      id: [''],
      reference: ['', Validators.required],
      amount: ['', Validators.required],
      transactionDate: ['', Validators.required],
      // @ts-ignore
      profilOf: ['', Validators.required],
       });

    if (id) {

      this.checkService.getCheckById(this.route.snapshot.params.id)
        .subscribe(bill => {
          // @ts-ignore
          this.check = check;

          // @ts-ignore
          this.checkForm = this.formBuilder.group({

            reference: [this.check.reference, Validators.required],
            amount: [this.check.amount, Validators.required],
            transactionDate: [this.check.transactionDate, Validators.required],

            // @ts-ignore
            profilOf: [this.check.profilOf, Validators.required],
            }, () => {
            this.error = true;
            this.loading = false;
          });
          this.loading = false;
        });
    } else {
      this.loading = false;
    }

  }


  editCheck() {
    this.loading = true;
console.log("1");
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


  addCheck() {
    this.submitted = true;
    this.loading = true;
    // @ts-ignore
    this.checkService.postCheck(this.checkForm.value)
      .subscribe(d => {
        this.loading = false;
        this.router.navigate(['/checks']);
        // @ts-ignore      }, () => {
        this.loading = false;
        this.error = true;
      });
  }
  onAddCheckClick() {

      this.addCheck();


  }


  onEditCheckClick() {
    console.log("h");
      this.editCheck();
    }
  }

