import { Injectable }                                   from '@angular/core';
import { HttpClient, HttpParams , HttpHeaders }         from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

import { LanguageUnit }                                 from '../language-unit/language-unit.class';


export class  NounInfo {
  id: number;
  hebrew: string;
  russian: string;
  childQuantity: number;


  constructor (){
    this.id = 0;
    this.hebrew = '';
    this.russian = '';
    this.childQuantity = 0;
  }
}

export class Noun{
  id: number;
  nounDataCollection: NounData[];

  constructor (){
    this.id = 0;
    this.nounDataCollection = [];
  }
}

export class NounData{
  id: number;
  genderRU: number;
  quantityRU: number;
  genderIL: number;
  quantityIL: number;
  languageUnit: LanguageUnit;

  constructor(){
    this.languageUnit = new LanguageUnit();
  }
}

@Injectable()
export class NounService {

  private nounsInfoUrl :string = '/ajax/nouns';
  private nounUrl :string = '/ajax/noun';

  private nounListFilter: object;

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
    console.log(noun);
    return this.http.post(this.nounUrl ,noun
        ,{headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  saveNounListFilter(nounListFilter: object){
    this.nounListFilter = nounListFilter;
  }

  getNounFilterList(){
    return this.nounListFilter;
  }
}