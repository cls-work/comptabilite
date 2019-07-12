import {AfterViewInit, Component, OnInit, Provider} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {QuotationService} from '../_services/quotation.service';
import {QuotationModel} from '../_models/quotation.model';
import {BASE_URL} from '../_globals/vars';
import {ProviderModel} from '../_models/provider.model';
import {ProviderService} from '../_services/provider.service';

declare let $: any;

@Component({
  selector: 'app-quotation-form',
  templateUrl: './quotation-form.component.html',
  styleUrls: ['./quotation-form.component.scss']
})
export class QuotationFormComponent implements OnInit, AfterViewInit {

  providers: ProviderModel[];
  providers_distinct_name: ProviderModel[];
  providers_filtred: ProviderModel[];
  providername: any;
  selectedProvider: any;
  providerForm: FormGroup;

  quotationForm: FormGroup;
  QuotationToSend :FormGroup;
  quotation: QuotationModel;

  loading: boolean;
  error: boolean;
  submitted: boolean;

  private token: string = JSON.parse(localStorage.getItem('currentUser')).accessToken;
  selectedPersonId: any;
  selectedSimpleItem: any;
  items = [true, 'Two', 3];
  simpleItems: any;
  submittedProvider: boolean;
  errorProvider: boolean;

  constructor(
    private providerService: ProviderService,
    private formBuilder: FormBuilder,
    private quotationService: QuotationService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.providerService.getAllProviders()
      .subscribe(
        (providers: any) => {
          this.providers = providers;
        });
    // this.loading = true;

    //  const id = this.route.snapshot.params.id;


    this.providerForm = this.formBuilder.group({
      name: ['', Validators.required],
      adresse: ['', Validators.required]

      // @ts-ignore
    });

    this.quotationForm = this.formBuilder.group({
      provider: ['', Validators.required],
      date: ['', Validators.required],
      taxStamp: ['', Validators.required],
      purchases: this.formBuilder.array([this.createItem()]),
      totalHT: [''],
      totalTTC: [''],
      totalTVA: ['']

      // @ts-ignore
    });

    /*
        if (id) {

          this.quotationService.getQuotationByID(this.route.snapshot.params.id)
            .subscribe(quotation => {
              // @ts-ignore
              this.quotation = quotation;

              // @ts-ignore
              this.quotationForm = this.formBuilder.group({
                documentIds: [],
                provider: [this.quotation.provider, Validators.required],
                date: [this.quotation.creattionDate, Validators.required],
                taxStamp: [this.quotation.taxStamp, Validators.required],

                // @ts-ignore
                checkPayment: [this.quotation.checkPayment === true ? '1' : '0', Validators.required],
                checkReference: [this.quotation.checkReference],
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
  }

  createItem(): FormGroup {
    return this.formBuilder.group({
      product: ['', Validators.required],
      quantity: ['', Validators.required],
      unitPrice: ['', Validators.required],
      discount: ['', Validators.required],
      tva: ['19', Validators.required],
      unitPriceAfterDiscount: [''],
      amountHT: [''],
      amountTVA: [''],
      amountTTC: [''],
    });
  }


  addQuotation() {

    /* this.submitted = true;
     this.loading = true;
     this.quotationForm.value.checkPayment = parseInt(this.quotationForm.value.checkPayment, 10);
     if (this.quotationForm.value.checkPayment === 0) {
       this.quotationForm.value.checkRefeerence = '';
     }
     // @ts-ignore*/


    this.quotationService.postQuotation(this.QuotationToSend.value)
      .subscribe(reponse => {
        console.log(reponse);
        this.loading = false;
        // @ts-ignore
        //  this.router.navigate(['/add-purchases', d.id, 'new']);
      }, () => {
        this.loading = false;
        this.error = true;
      });
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


        baseContext.QuotationToSend = baseContext.formBuilder.group({
          documentIds : [data.response.map((item)=> item.id)],
          quotation : [baseContext.quotationForm.value]
        }
      );


      // baseContext.quotationForm.setValue({fileStorageProperties : data.response}) ;
     console.log(data);
     console.log('hamza');

     console.log(baseContext.quotationForm.value);

     if (!baseContext.quotation) {
        baseContext.addQuotation();
      } else {
        // baseContext.editQuotation();
      }

    });
  }


  uploadFiles() {
    $('#test').fileinput('upload').fileinput('disable');
  }

  onAddBillClick() {
    console.log('test1');
    console.log(this.quotationForm.value);
    const filesCount = $('#test').fileinput('getFilesCount');

    console.log('test2');
    if (filesCount > 0) {

      this.uploadFiles();
      console.log('files>0.log');
      // bill will be sended automatically when files are uploaded

    }

    console.log('test3');
    if (filesCount == 0 && !this.quotation) {
      console.log('add bill');
      this.addQuotation();
    }

  }


  ProviderChange($evenet: any) {
    console.log('provider cahnged ');
    this.providername = this.selectedProvider;
    // use service to get provider by adress
    this.providers_filtred = this.providers
      .filter((item) => {
        console.log(item);
        return item.name == this.selectedProvider.name;
      });

  }

  addProvider() {
    console.log(this.providerForm.value);



    this.providerService.postProvider(this.providerForm.value)
      .subscribe((res) => {
          console.log('provider added');
          this.errorProvider = false;
          this.submittedProvider = true;
          setTimeout(() => {this.submittedProvider = false; } , 3000);
          this.providerService.getAllProviders()
            .subscribe(
              (providers: any) => {
                this.providers = providers;
              });
        }
        ,
        () => {
          this.errorProvider = true;
          this.submittedProvider = true;
          setTimeout(() => {this.submittedProvider = false; } , 3000);
          console.log('erreur');
        }
      );
  }


}
