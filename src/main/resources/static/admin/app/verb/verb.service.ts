import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { LanguageUnit }                                 from '../language-unit/language-unit.class'

export class  VerbInfo {
  id: number;
  hebrew: string;
  russian: string;
  actionVerb: boolean;
  childQuantity: number;


  constructor (){
    this.id = 0;
    this.hebrew = '';
    this.russian = '';
    this.actionVerb = false;
    this.childQuantity = 0;
  }
}

export class Verb{
  id: number;
  infinitive : LanguageUnit;
  verbDataCollection: VerbData[];

  constructor (){
    this.id = 0;
    this.infinitive = new LanguageUnit();
    this.verbDataCollection = [];
  }
}

export class VerbData{
  id: number;
  gender: number;
  quantity: number;
  time: number;
  person: number;
  languageUnit: LanguageUnit;

  constructor(){
    this.languageUnit = new LanguageUnit();
  }
}

@Injectable()
export class VerbService {

  private verbsInfoUrl :string = '/ajax/verbs';
  private verbUrl :string = '/ajax/verb';
  private changeActionVerbUrl :string = '/ajax/changeActionVerb';

  private verbListFilter: object;

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

  saveVerbListFilter(verbListFilter: object){
    this.verbListFilter = verbListFilter;
  }

  getVerbFilterList(){
    return this.verbListFilter;
  }
}