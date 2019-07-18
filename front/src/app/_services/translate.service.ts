import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  data: any = {};

  langSubject: Subject<string>;

  constructor(private http: HttpClient) {
    this.langSubject = new Subject();
  }

  // set app language
  use(lang: string): Promise<{}> {
    if (lang) {
      localStorage.setItem('lang', lang);

      this.langSubject.next(lang);
    }
    return new Promise<{}>((resolve) => {
          const langPath = `assets/i18n/${lang || localStorage.getItem('lang') || 'en'}.json`;

          this.http.get<{}>(langPath).subscribe(
          translation => {
            this.data = translation;

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
