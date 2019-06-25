import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';
import {BASE_URL} from "../_globals/vars";

declare let $: any;

@Component({
  selector: 'app-bill-form',
  templateUrl: './bill-form.component.html',
  styleUrls: ['./bill-form.component.scss']
})
export class BillFormComponent implements OnInit, AfterViewInit {


  billForm: FormGroup;
  isCheckPayment = false;
  bill: BillModel;
  loading: boolean;

  private token: string = JSON.parse(localStorage.getItem('currentUser')).accessToken;


  constructor(private formBuilder: FormBuilder,
              private billService: BillService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    const id = this.route.snapshot.params.id;


    this.billForm = this.formBuilder.group({
      documentIds: [],
      provider: ['', Validators.required],
      date: ['', Validators.required],
      taxStamp: ['', Validators.required],
      // @ts-ignore
      checkPayment: ['0', Validators.required],
      checkReference: [''],
    });

    if (id) {

      this.billService.getBillByID(this.route.snapshot.params.id)
        .subscribe(bill => {
          // @ts-ignore
          this.bill = bill;
          console.log(this.bill);

          // @ts-ignore
          this.billForm = this.formBuilder.group({
            provider: [this.bill.provider, Validators.required],
            date: [this.bill.date, Validators.required],
            taxStamp: [this.bill.taxStamp, Validators.required],
            // @ts-ignore
            checkPayment: [this.bill.checkPayment === true ? '1' : '0', Validators.required],
            checkReference: [this.bill.checkReference],
          });
          this.loading = false;
        });
    } else {
      this.loading = false;
    }

  }



  editBill() {
    this.loading = true;

    const billId = this.route.snapshot.params.id;


    this.billForm.value.checkPayment = parseInt(this.billForm.value.checkPayment, 10);
    if (this.billForm.value.checkPayment === 0) {
      this.billForm.value.checkRefeerence = '';
    }
    this.billService.editBill(billId, Object.assign({billId}, this.billForm.value))
      .subscribe(d => {
        this.loading = false;
        this.router.navigate(['/bills']);
      });

  }

  addBill() {
    this.loading = true;
    this.billForm.value.checkPayment = parseInt(this.billForm.value.checkPayment, 10);
    if (this.billForm.value.checkPayment === 0) {
      this.billForm.value.checkRefeerence = '';
    }
    // @ts-ignore
    this.billService.postBill(this.billForm.value)
      .subscribe(d => {
        this.loading = false;
        // @ts-ignore
        this.router.navigate(['/add-products', d.billId]);
      });
  }

  editCheckPayment() {
    return parseInt(this.billForm.value.checkPayment, 10) !== 0;
  }

  ngAfterViewInit(): void {
    $('#test').fileinput({
      theme: "explorer-fas",
      uploadUrl: BASE_URL + "uploadMultipleFiles",
      ajaxSettings: {headers: {'Authorization': 'Bearer ' + this.token}},
      uploadAsync: false,
      overwriteInitial: false,
      previewFileIcon: '<i class="fas fa-file"></i>',
      initialPreviewAsData: true, // defaults markup
      preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
      previewFileIconSettings: { // configure your icon file extensions
        'doc': '<i class="fas fa-file-word text-primary"></i>',
        'xls': '<i class="fas fa-file-excel text-success"></i>',
        'ppt': '<i class="fas fa-file-powerpoint text-danger"></i>',
        'pdf': '<i class="fas fa-file-pdf text-danger"></i>',
        'zip': '<i class="fas fa-file-archive text-muted"></i>',
        'htm': '<i class="fas fa-file-code text-info"></i>',
        'txt': '<i class="fas fa-file-alt text-info"></i>',
        'mov': '<i class="fas fa-file-video text-warning"></i>',
        'mp3': '<i class="fas fa-file-audio text-warning"></i>',
        // note for these file types below no extension determination logic
        // has been configured (the keys itself will be used as extensions)
        'jpg': '<i class="fas fa-file-image text-danger"></i>',
        'gif': '<i class="fas fa-file-image text-muted"></i>',
        'png': '<i class="fas fa-file-image text-primary"></i>'
      },
      previewFileExtSettings: { // configure the logic for determining icon file extensions
        'doc': function (ext) {
          return ext.match(/(doc|docx)$/i);
        },
        'xls': function (ext) {
          return ext.match(/(xls|xlsx)$/i);
        },
        'ppt': function (ext) {
          return ext.match(/(ppt|pptx)$/i);
        },
        'zip': function (ext) {
          return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
        },
        'htm': function (ext) {
          return ext.match(/(htm|html)$/i);
        },
        'txt': function (ext) {
          return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
        },
        'mov': function (ext) {
          return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
        },
        'mp3': function (ext) {
          return ext.match(/(mp3|wav)$/i);
        }
      }
    });


    const baseContext = this;
    $('#test').on('filebatchuploadsuccess', function (event, data) {

      baseContext.billForm.patchValue({
          documentIds: data.response.map(elt => elt.id)
        }
      );
      console.log(data.response.map(elt => elt.id));
      console.log(baseContext.billForm)

      // data.response.forEach(elt => baseContext.formBuilde).push(elt.id));
    });
  }
}
