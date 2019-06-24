import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  data: any = {};

  components = [
    'header',
    'bills'
  ];

  constructor(private http: HttpClient) {
  }

  use(lang: string) {
    // this.data = {};
    // console.log('abc');
    for (const component of this.components) {
      this.componentLang(lang, component);
    }
  }

  componentLang(lang: string, component: string): Promise<{}> {
    return new Promise<{}>((resolve) => {
          const langPath = `assets/i18n/${lang || 'fr'}/${component}.json`;
          // console.log(langPath);

          this.http.get<{}>(langPath).subscribe(
          translation => {
            // console.log(Object.keys(translation));
            for (const key of Object.keys(translation)) {
              // @ts-ignore
              // console.log(translation[key]);
              // console.log(key);
              this.data[key] = translation[key];
            }
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
