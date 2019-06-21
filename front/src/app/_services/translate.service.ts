import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  data: any = {};

  components = [
    'header',
    'bills',
    'bill-form'
  ];

  constructor(private http: HttpClient) {
  }

 /* use(lang: string) {
    // this.data = {};
    // console.log('abc');
    for (const component of this.components) {
      this.componentLang(lang, component);
    }
  }*/

  use(lang: string): Promise<{}> {
    return new Promise<{}>((resolve) => {
          const langPath = `assets/i18n/${lang || 'fr'}.json`;
          console.log(langPath);

          this.http.get<{}>(langPath).subscribe(
          translation => {
            console.log(translation);
            console.log('hello');
            // console.log(Object.keys(translation));
            this.data = translation;
            // console.log(this.data);
            resolve(this.data);
          },
          () => {
            this.data = {};
            resolve(this.data);
          }
        );

    });
  }

}
