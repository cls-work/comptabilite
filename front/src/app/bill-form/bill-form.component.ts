import {AfterViewInit, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BillService} from '../_services/bill.service';
import {BillModel} from '../_models/bill.model';
import {ActivatedRoute, Router} from '@angular/router';
import {BASE_URL, ERROR_BILL_FORM} from '../_globals/vars';

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
  error: boolean;
  submitted: boolean;

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

          // @ts-ignore
          this.billForm = this.formBuilder.group({
            documentIds: [],
            date: [this.bill.creationDate, Validators.required],

            // @ts-ignore
            checkPayment: [this.bill.checkPayment === true ? '1' : '0', Validators.required],
            checkReference: [this.bill.checkReference],
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


  editBill() {
    this.loading = true;

    const billId = this.route.snapshot.params.id;


    this.billForm.value.checkPayment = parseInt(this.billForm.value.checkPayment, 10);
    if (this.billForm.value.checkPayment === 0) {
      this.billForm.value.checkRefeerence = '';
    }
    this.billService.editBill(billId, Object.assign({billId}, this.billForm.value))
      .subscribe(() => {
        this.loading = false;
        this.router.navigate(['/bills', 'edited']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }


  addBill() {
    this.submitted = true;
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
        this.router.navigate(['/add-products', d.billId, 'new']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
  }

  editCheckPayment() {
    return parseInt(this.billForm.value.checkPayment, 10) !== 0;
  }

  ngAfterViewInit(): void {

    this.initFileUploader();

  }


  initFileUploader() {
    $('#test').fileinput({
      theme: 'explorer-fas',
      uploadUrl: BASE_URL + 'uploadMultipleFiles',
      ajaxSettings: {headers: {Authorization: 'Bearer ' + this.token}},
      uploadAsync: false,
      overwriteInitial: false,
      showCaption: false,
      showRemove: true,
      showUpload: false,
      showCancel: false,
      language: 'fr',
      previewFileIcon: '<i class="fas fa-file"></i>',
      initialPreviewAsData: true, // defaults markup
      preferIconicPreview: true, // this will force thumbnails to display icons for following file extensions
      previewFileIconSettings: { // configure your icon file extensions
        doc: '<i class="fas fa-file-word text-primary"></i>',
        xls: '<i class="fas fa-file-excel text-success"></i>',
        ppt: '<i class="fas fa-file-powerpoint text-danger"></i>',
        pdf: '<i class="fas fa-file-pdf text-danger"></i>',
        zip: '<i class="fas fa-file-archive text-muted"></i>',
        htm: '<i class="fas fa-file-code text-info"></i>',
        txt: '<i class="fas fa-file-alt text-info"></i>',
        mov: '<i class="fas fa-file-video text-warning"></i>',
        mp3: '<i class="fas fa-file-audio text-warning"></i>',
        // note for these file types below no extension determination logic
        // has been configured (the keys itself will be used as extensions)
        jpg: '<i class="fas fa-file-image text-danger"></i>',
        gif: '<i class="fas fa-file-image text-muted"></i>',
        png: '<i class="fas fa-file-image text-primary"></i>'
      },
      previewFileExtSettings: { // configure the logic for determining icon file extensions
        doc(ext) {
          return ext.match(/(doc|docx)$/i);
        },
        xls(ext) {
          return ext.match(/(xls|xlsx)$/i);
        },
        ppt(ext) {
          return ext.match(/(ppt|pptx)$/i);
        },
        zip(ext) {
          return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
        },
        htm(ext) {
          return ext.match(/(htm|html)$/i);
        },
        txt(ext) {
          return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
        },
        mov(ext) {
          return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
        },
        mp3(ext) {
          return ext.match(/(mp3|wav)$/i);
        }
      }
    });


    const baseContext = this;
    $('#test').on('filebatchuploadsuccess', function(event, data) {

      baseContext.billForm.patchValue({
          documentIds: data.response.map(elt => elt.id)
        }
      );

      if (!baseContext.bill) {
        baseContext.addBill();
      } else {
        baseContext.editBill();
      }

      // data.response.forEach(elt => baseContext.formBuilde).push(elt.id));
    });
  }


  uploadFiles() {
    $('#test').fileinput('upload').fileinput('disable');
  }

  onAddBillClick() {
    console.log("test1");
    let filesCount = $('#test').fileinput('getFilesCount');

    console.log("test2");
    if (filesCount > 0) {

      this.uploadFiles();
      console.log('files>0.log');
      // bill will be sended automatically when files are uploaded

    }

    console.log("test3");
    if (filesCount == 0 && !this.bill) {
      console.log('add bill');
      this.addBill();
    }

  }


  onEditBillClick() {
    let filesCount = $('#test').fileinput('getFilesCount');


    if (filesCount > 0) {
      this.uploadFiles();
      // bill will be sended automatically when files are uploaded
    }
    if (filesCount == 0 && this.bill) {
      this.editBill();
    }
  }

}
