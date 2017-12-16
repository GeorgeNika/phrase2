import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { Adverb }                                         from '../model/adverb/adverb.class';
import { AdverbInfo }                                     from '../model/adverb/adverb_info.class';
import { PageParameters }                               from '../model/page_parameters.class';

@Injectable()
export class AdverbService {

  private adverbsInfoUrl :string = '/ajax/adverbs';
  private adverbUrl :string = '/ajax/adverb';

  private adverbPageParameters: PageParameters;

  constructor(private http: HttpClient) {}

  getAdverbInfoList(page: number, itemsOnPage: number, filter: string) : Observable<AdverbInfo[]> {

    const searchParams = new HttpParams()
        .set('page', page)
        .set('itemsOnPage', itemsOnPage)
        .set('filter', filter);

    return this.http.get(this.adverbsInfoUrl, {params: searchParams});
  }

  getAdverbById(adverbId: number): Observable<Adverb> {
    if (adverbId == 0){
      return Observable.create(observer => {
        observer.next(new Adverb());
        observer.complete();
      });
    }
    return this.http.get(this.adverbUrl.concat('/').concat(adverbId.toString()));
  }

  saveAdverb(adverb: Adverb): Observable<number>{
    return this.http.post(this.adverbUrl ,adverb
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  saveAdverbPageParameters(adverbPageParameters: PageParameters){
    this.adverbPageParameters = adverbPageParameters;
  }

  getAdverbPageParameters(){
    return this.adverbPageParameters;
  }
}