import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { Noun }                                         from '../model/noun/noun.class';
import { NounInfo }                                     from '../model/noun/noun_info.class';
import { PageParameters }                               from '../model/page_parameters.class';

@Injectable()
export class NounService {

  private nounsInfoUrl :string = '/ajax/nouns';
  private nounUrl :string = '/ajax/noun';

  private nounPageParameters: PageParameters;

  constructor(private http: HttpClient) {}

  getNounInfoList(page: number, itemsOnPage: number, filter: string) : Observable<NounInfo[]> {

    const searchParams = new HttpParams()
        .set('page', page)
        .set('itemsOnPage', itemsOnPage)
        .set('filter', filter);

    return this.http.get(this.nounsInfoUrl, {params: searchParams});
  }

  getNounById(nounId: number): Observable<Noun> {
    if (nounId == 0){
      return Observable.create(observer => {
        observer.next(new Noun());
        observer.complete();
      });
    }
    return this.http.get(this.nounUrl.concat('/').concat(nounId.toString()));
  }

  saveNoun(noun: Noun): Observable<number>{
    return this.http.post(this.nounUrl ,noun
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  saveNounPageParameters(nounPageParameters: PageParameters){
    this.nounPageParameters = nounPageParameters;
  }

  getNounPageParameters(){
    return this.nounPageParameters;
  }
}