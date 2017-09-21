import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { slideInDownAnimation } from '../animations';

import { Verb, VerbData, VerbService }  from './verb.service';

@Component({
  template: `
    <div class="container-fluid">
        <div *ngIf="verb">
            <div class="row justify-content-between">
                <h3>Verb id {{verb.id}}</h3>
            </div>
            <br/>
            <div class="row justify-content-between">
                <div class="col-2">"infinitive"</div>
                <input [(ngModel)]="verb.infinitive.hebrew" class="col-3" placeholder="hebrew"/>
                <input [(ngModel)]="verb.infinitive.russian" class="col-3" placeholder="russian"/>
                <input [(ngModel)]="verb.infinitive.transcription" class="col-3" placeholder="transcription"/>
            </div>
            <br/>
            <br/>
            <div *ngFor="let verbData of verb.verbDataCollection">
                <div class="row justify-content-between">
                    <div class="col-1">
                        <selectTimeComponent 
                            [time]="verbData.time" 
                            (timeChange)="timeChange($event, verbData)">  
                        </selectTimeComponent>
                    </div>
                    <div class="col-1">                        
                        <selectPersonComponent 
                            [person]="verbData.person" 
                            (personChange)="personChange($event, verbData)">  
                        </selectPersonComponent>
                    </div>
                    <div class="col-1">
                        <selectQuantityComponent 
                            [quantity]="verbData.quantity" 
                            (quantityChange)="quantityChange($event, verbData)">  
                        </selectQuantityComponent>
                    </div>
                    <div class="col-1">
                        <selectGenderComponent 
                            [gender]="verbData.gender" 
                            (genderChange)="genderChange($event, verbData)">  
                        </selectGenderComponent>
                    </div>
                    <input [(ngModel)]="verbData.languageUnit.hebrew" class="col-2" placeholder="hebrew"/>
                    <input [(ngModel)]="verbData.languageUnit.russian" class="col-2" placeholder="russian"/>
                    <input [(ngModel)]="verbData.languageUnit.transcription" class="col-2" placeholder="transcription"/>
                    <div class="col-1">buttons</div>
                </div> 
            </div>  
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
    private service: VerbService
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
    this.service.saveVerb(this.verb).subscribe((data) => console.log(data) );
    this.gotoVerbs();
  }

  timeChange(time: number, verbData: any ){
    verbData.time = time;
  }

  personChange(person: number, verbData: any ){
    verbData.person = person;
  }

  quantityChange(quantity: number, verbData: any ){
    verbData.quantity = quantity;
  }

  genderChange(gender: number, verbData: any ){
    verbData.gender = gender;
  }
}