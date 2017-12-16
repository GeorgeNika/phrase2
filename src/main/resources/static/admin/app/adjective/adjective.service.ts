import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { Adjective }                                    from '../model/adjective/adjective.class';
import { AdjectiveInfo }                                from '../model/adjective/adjective_info.class';
import { PageParameters }                               from '../model/page_parameters.class';

@Injectable()
export class AdjectiveService {

  private adjectivesInfoUrl :string = '/ajax/adjectives';
  private adjectiveUrl :string = '/ajax/adjective';

  private adjectivePageParameters: PageParameters;

  constructor(private http: HttpClient) {}

  getAdjectiveInfoList(page: number, itemsOnPage: number, filter: string) : Observable<AdjectiveInfo[]> {

    const searchParams = new HttpParams()
        .set('page', page)
        .set('itemsOnPage', itemsOnPage)
        .set('filter', filter);

    return this.http.get(this.adjectivesInfoUrl, {params: searchParams});
  }

  getAdjectiveById(adjectiveId: number): Observable<Adjective> {
    if (adjectiveId == 0){
      return Observable.create(observer => {
        observer.next(new Adjective());
        observer.complete();
      });
    }
    return this.http.get(this.adjectiveUrl.concat('/').concat(adjectiveId.toString()));
  }

  saveAdjective(adjective: Adjective): Observable<number>{
    return this.http.post(this.adjectiveUrl ,adjective
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  saveAdjectivePageParameters(adjectivePageParameters: PageParameters){
    this.adjectivePageParameters = adjectivePageParameters;
  }

  getAdjectivePageParameters(){
    return this.adjectivePageParameters;
  }
}