import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { Verb }                                         from '../model/verb/verb.class';
import { VerbInfo }                                     from '../model/verb/verb_info.class'
import { PageParameters }                               from "../model/page_parameters.class";

@Injectable()
export class VerbService {

  private verbsInfoUrl :string = '/ajax/verbs';
  private verbUrl :string = '/ajax/verb';
  private changeActionVerbUrl :string = '/ajax/changeActionVerb';

  private verbPageParameters: PageParameters;

  constructor(private http: HttpClient) {}

  getVerbInfoList(page: number, itemsOnPage: number, filter: string) : Observable<VerbInfo[]> {

    const searchParams = new HttpParams()
        .set('page', page)
        .set('itemsOnPage', itemsOnPage)
        .set('filter', filter);

    return this.http.get(this.verbsInfoUrl, {params: searchParams});
  }

  getVerbById(verbId: number): Observable<Verb> {
    if (verbId == 0){
      return Observable.create(observer => {
        observer.next(new Verb());
        observer.complete();
      });
    }
    return this.http.get(this.verbUrl.concat('/').concat(verbId.toString()));
  }

  saveVerb(verb: Verb): Observable<number>{
    return this.http.post(this.verbUrl ,verb
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  changeActionVerb(id: number): Observable<boolean>{
    return this.http.post(this.changeActionVerbUrl ,id
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  saveVerbPageParameters(verbPageParameters: PageParameters){
    this.verbPageParameters = verbPageParameters;
  }

  getVerbPageParameters(){
    return this.verbPageParameters;
  }
}