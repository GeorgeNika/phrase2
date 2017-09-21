import { Injectable }                                   from '@angular/core';
import { HttpClient, RequestOptions , HttpHeaders }     from "@angular/common/http";
import { Observable }                                   from 'rxjs/Observable';
import { of }                                           from 'rxjs/observable/of';

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

class LanguageUnit{
  id: number;
  hebrew: string;
  russian: string;
  transcription: string;
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

  constructor(private http: HttpClient) {}

  getVerbInfoList() : Observable<VerbInfo[]> {
    return this.http.get(this.verbsInfoUrl);
  }

  getVerbById(verbId: number): Observable<Verb> {
    console.log(verbId);
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
}