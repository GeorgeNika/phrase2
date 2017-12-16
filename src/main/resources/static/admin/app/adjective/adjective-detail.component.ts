import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding }   from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { slideInDownAnimation }             from '../animations';

import { AdjectiveService }                 from './adjective.service';
import { Adjective }                        from '../model/adjective/adjective.class';
import { AdjectiveData }                    from '../model/adjective/adjective_data.class';
import { AlertService }                     from "../useful/alert/alert.service";

@Component({
  template: `
    <div class="container-fluid">
        <div *ngIf="adjective">
            <div class="row justify-content-between">
                <h3>Adjective id {{adjective.id}}</h3>
            </div>
            <br/>
            <br/>
            <table class="table table-hover">
                <thead>
                    <tr class="row bg-secondary">
                        <td class="col-2">gender</td>
                        <td class="col-1">quantity</td>
                        <td class="col-3">russian</td>
                        <td class="col-3">voice</td>
                        <td class="col-3 text-right">hebrew</td>
                    </tr> 
                </thead>
                <tbody>
                    <tr *ngFor="let adjectiveData of adjective.adjectiveDataCollection" class="row justify-content-between align-items-center">
                        <td class="col-2">
                            <selectGenderComponent
                                [gender]="adjectiveData.gender" 
                                (genderChange)="genderChange($event, adjectiveData)">  
                            </selectGenderComponent>
                        </td>
                        <td class="col-1">
                            <selectQuantityComponent 
                                [quantity]="adjectiveData.quantity" 
                                (quantityChange)="quantityChange($event, adjectiveData)">  
                            </selectQuantityComponent>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="adjectiveData.languageUnit.russian" style="padding: 0; max-width: 100%"
                            placeholder="russian"/>
                        </td>
                        <td class="col-3">
                            <input [(ngModel)]="adjectiveData.languageUnit.transcription" style="padding: 0; max-width: 100%"
                            placeholder="transcription"/>
                        </td>
                        <td class="col-3 text-right">
                            <input [(ngModel)]="adjectiveData.languageUnit.hebrew" style="padding: 0; max-width: 100%" 
                            class="text-right" placeholder="hebrew"/>
                        </td>
                    </tr> 
                </tbody>  
            </table>    
            <br/>
            <div class="row justify-content-between">
                <button (click)="addAdjectiveData()" class="col-3 btn btn-form btn-primary">Add adjective data</button>
                <button (click)="saveAdjectiveAndClose()" class="col-3 btn btn-form btn-success">Save and Close</button>
                <button (click)="gotoAdjectives()" class="col-3 btn btn-form btn-danger">CLOSE</button>
            </div>
        </div>
    </div>
  `,
  animations: [ slideInDownAnimation ]
})
export class AdjectiveDetailComponent implements OnInit {
  @HostBinding('@routeAnimation') routeAnimation = true;

  adjective: Adjective;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AdjectiveService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) =>
        this.service.getAdjectiveById(params.get('id')))
      .subscribe((adjective: Adjective) => this.adjective = adjective);
  }

  gotoAdjectives() {
    let adjectiveId = this.adjective ? this.adjective.id : null;
    this.router.navigate(['/adjectives']);
  }

  addAdjectiveData(){
    let adjectiveData = new AdjectiveData();
    this.adjective.adjectiveDataCollection.push(adjectiveData);
  }

  saveAdjectiveAndClose(){
    this.service.saveAdjective(this.adjective).subscribe(
        (data) => console.log("save adjective with id "+data),
        (error) => {this.alertService.error("Error during saving adjective", true)}
        );
    this.gotoAdjectives();
  }

  genderChange(gender: number, adjectiveData: AdjectiveData ){
    adjectiveData.gender = gender;
  }

  quantityChange(quantity: number, adjectiveData: AdjectiveData ){
    adjectiveData.quantity = quantity;
  }
}