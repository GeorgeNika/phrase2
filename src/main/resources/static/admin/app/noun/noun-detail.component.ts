import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding }   from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { slideInDownAnimation }             from '../animations';

import { NounService }                      from './noun.service';
import { Noun }                             from '../model/noun/noun.class';
import { NounData }                         from '../model/noun/noun_data.class';
import { AlertService }                     from "../useful/alert/alert.service";

@Component({
  template: `
    <div class="container-fluid">
        <div *ngIf="noun">
            <div class="row justify-content-between">
                <h3>Noun id {{noun.id}}</h3>
            </div>
            <br/>
            <br/>
            <table class="table table-hover">
                <thead>
                    <tr class="row bg-success">
                        <td class="col-1">gndr RU</td>
                        <td class="col-1">qty RU</td>
                        <td class="col-3">russian</td>
                        <td class="col-3">voice</td>
                        <td class="col-2 text-right">hebrew</td>
                        <td class="col-1 text-right">gndr IL</td>
                        <td class="col-1 text-right">qty IL</td>
                    </tr> 
                </thead>
                <tbody>
                    <tr *ngFor="let nounData of noun.nounDataCollection" class="row justify-content-between align-items-center">
                        <td class="col-1">
                            <selectGenderComponent
                                [gender]="nounData.genderRU" 
                                (genderChange)="genderRUChange($event, nounData)">  
                            </selectGenderComponent>
                        </td>
                        <td class="col-1">
                            <selectQuantityComponent 
                                [quantity]="nounData.quantityRU" 
                                (quantityChange)="quantityRUChange($event, nounData)">  
                            </selectQuantityComponent>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="nounData.languageUnit.russian" style="padding: 0; max-width: 100%" 
                            placeholder="russian"/>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="nounData.languageUnit.transcription" style="padding: 0; max-width: 100%" 
                            placeholder="transcription"/>
                        </td>
                        <td class="col-2 text-right">
                            <input [(ngModel)]="nounData.languageUnit.hebrew" style="padding: 0; max-width: 100%"
                            class="text-right" placeholder="hebrew"/>
                        </td>
                        <td class="col-1">
                            <selectGenderComponent
                                [gender]="nounData.genderIL" 
                                (genderChange)="genderILChange($event, nounData)">  
                            </selectGenderComponent>
                        </td>
                        <td class="col-1">
                            <selectQuantityComponent 
                                [quantity]="nounData.quantityIL" 
                                (quantityChange)="quantityILChange($event, nounData)">  
                            </selectQuantityComponent>
                        </td>
                    </tr> 
                </tbody>  
            </table>    
            <br/>
            <div class="row justify-content-between">
                <button (click)="addNounData()" class="col-3 btn btn-form btn-primary">Add noun data</button>
                <button (click)="saveNounAndClose()" class="col-3 btn btn-form btn-success">Save and Close</button>
                <button (click)="gotoNouns()" class="col-3 btn btn-form btn-danger">CLOSE</button>
            </div>
        </div>
    </div>
  `,
  animations: [ slideInDownAnimation ]
})
export class NounDetailComponent implements OnInit {
  @HostBinding('@routeAnimation') routeAnimation = true;

  noun: Noun;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: NounService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) =>
        this.service.getNounById(params.get('id')))
      .subscribe((noun: Noun) => this.noun = noun);
  }

  gotoNouns() {
    let nounId = this.noun ? this.noun.id : null;
    this.router.navigate(['/nouns']);
  }

  addNounData(){
    let nounData = new NounData();
    this.noun.nounDataCollection.push(nounData);
  }

  saveNounAndClose(){
    this.service.saveNoun(this.noun).subscribe(
        (data) => console.log("save noun with id "+data),
        (error) => {this.alertService.error("Error during saving noun", true)}
        );
    this.gotoNouns();
  }

  genderRUChange(gender: number, nounData: NounData ){
    nounData.genderRU = gender;
  }

  quantityRUChange(quantity: number, nounData: NounData ){
    nounData.quantityRU = quantity;
  }

  genderILChange(gender: number, nounData: NounData ){
    if (gender > 2){
      this.alertService.error("incorrect hebrew gender", true);
      return;
    }
    nounData.genderIL = gender;
  }

  quantityILChange(quantity: number, nounData: NounData ){
    nounData.quantityIL = quantity;
  }

}