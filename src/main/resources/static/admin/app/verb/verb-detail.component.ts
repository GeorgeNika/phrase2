import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding }     from '@angular/core';
import { Router, ActivatedRoute, ParamMap }   from '@angular/router';

import { slideInDownAnimation }               from '../animations';

import { VerbService }                        from './verb.service';
import { Verb }                               from '../model/verb/verb.class';
import { VerbData }                           from '../model/verb/verb_data.class';
import { Pronoun }                            from '../useful/select-pronoun/select-pronoun.component'
import { AlertService }                       from "../useful/alert/alert.service";

@Component({
  template: `
    <div class="container-fluid">
        <div *ngIf="verb">
            <div class="row justify-content-between">
                <h3>Verb id {{verb.id}}</h3>
            </div>
            <br/>
            <div class="row justify-content-between align-items-center">
                <div class="col-2">infinitive - </div>
                <input [(ngModel)]="verb.infinitive.russian" class="col-3" placeholder="russian"/>
                <input [(ngModel)]="verb.infinitive.transcription" class="col-3" placeholder="transcription"/>
                <input [(ngModel)]="verb.infinitive.hebrew" class="col-3 text-right" placeholder="hebrew"/>
            </div>
            <br/>
            <div class="row justify-content-between align-items-center">
                <div class="col-2">preposition - </div>
                <input [(ngModel)]="verb.preposition.russian" class="col-3" placeholder="russian"/>
                <input [(ngModel)]="verb.preposition.transcription" class="col-3" placeholder="transcription"/>
                <input [(ngModel)]="verb.preposition.hebrew" class="col-3 text-right" placeholder="hebrew"/>
            </div>
            <br/>
            <br/>
            <table class="table table-hover">
                <thead>
                    <tr class="row justify-content-between align-items-center bg-info">
                        <td class="col-2">time</td>
                        <td class="col-1">pronoun</td>
                        <td class="col-3">russian</td>
                        <td class="col-3">voice</td>
                        <td class="col-3 text-right">hebrew</td>
                    </tr> 
                </thead>
                <tbody>
                    <tr *ngFor="let verbData of verb.verbDataCollection" class="row justify-content-between align-items-center">
                        <td class="col-2">
                            <selectTimeComponent 
                                [time]="verbData.time" 
                                (timeChange)="timeChange($event, verbData)">  
                            </selectTimeComponent>
                        </td>
                        <td class="col-1">
                            <selectPronounComponent
                                [pronoun] = "{person: verbData.person, gender: verbData.gender, quantity: verbData.quantity}"
                                (pronounChange)="pronounChange($event, verbData)">
                            </selectPronounComponent>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="verbData.languageUnit.russian"  style="padding: 0" 
                                placeholder="russian"/>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="verbData.languageUnit.transcription" style="padding: 0" 
                                placeholder="transcription"/>
                        </td>
                        <td class="col-3 text-right">
                            <input [(ngModel)]="verbData.languageUnit.hebrew" style="padding: 0" 
                                class="text-right" placeholder="hebrew"/>
                        </td>
                    </tr>  
                </tbody>
            </table>
            <br/>
            <div class="row justify-content-between">
                <button (click)="addVerbData()" class="col-3 btn btn-form btn-primary">Add verb data</button>
                <button (click)="saveVerbAndClose()" class="col-3 btn btn-form btn-success">Save and Close</button>
                <button (click)="gotoVerbs()" class="col-3 btn btn-form btn-danger">CLOSE</button>
            </div>
        </div>
    </div>
  `,
  animations: [ slideInDownAnimation ]
})
export class VerbDetailComponent implements OnInit {
  @HostBinding('@routeAnimation') routeAnimation = true;

  verb: Verb;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: VerbService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) =>
        this.service.getVerbById(params.get('id')))
      .subscribe((verb: Verb) => this.verb = verb);
  }

  gotoVerbs() {
    let verbId = this.verb ? this.verb.id : null;
    this.router.navigate(['/verbs']);
  }

  addVerbData(){
    let verbData = new VerbData();
    this.verb.verbDataCollection.push(verbData);
  }

  saveVerbAndClose(){
    this.service.saveVerb(this.verb).subscribe(
        (data) => console.log(data),
        (error) => {this.alertService.error("Error during saving verb", true)}
        );
    this.gotoVerbs();
  }

  timeChange(time: number, verbData: VerbData ){
    verbData.time = time;
  }

  personChange(person: number, verbData: VerbData ){
    verbData.person = person;
  }

  quantityChange(quantity: number, verbData: VerbData ){
    verbData.quantity = quantity;
  }

  genderChange(gender: number, verbData: VerbData ){
    verbData.gender = gender;
  }

  pronounChange(pronoun: Pronoun, verbData: VerbData){
    if (pronoun) {
      verbData.person = pronoun.person;
      verbData.gender = pronoun.gender;
      verbData.quantity = pronoun.quantity;
    }
  }
}