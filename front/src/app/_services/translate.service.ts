import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  data: any = {};


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
    if (lang) {
      localStorage.setItem('lang', lang);
    }
    return new Promise<{}>((resolve) => {
          const langPath = `assets/i18n/${lang || localStorage.getItem('lang') || 'en'}.json`;
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
