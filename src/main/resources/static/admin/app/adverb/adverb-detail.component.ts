import 'rxjs/add/operator/switchMap';
import { Component, OnInit, HostBinding }   from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { slideInDownAnimation }             from '../animations';

import { AdverbService }                    from './adverb.service';
import { Adverb }                           from '../model/adverb/adverb.class';
import { AlertService }                     from "../useful/alert/alert.service";

@Component({
  template: `
    <div class="container-fluid">
        <div *ngIf="adverb">
            <div class="row justify-content-between">
                <h3>Adverb id {{adverb.id}}</h3>
            </div>
            <br/>
            <div class="row justify-content-between align-items-center">
                <div class="col-2">mainForm - </div>
                <input [(ngModel)]="adverb.mainForm.russian" class="col-3" placeholder="russian"/>
                <input [(ngModel)]="adverb.mainForm.transcription" class="col-3" placeholder="transcription"/>
                <input [(ngModel)]="adverb.mainForm.hebrew" class="col-3 text-right" placeholder="hebrew"/>
            </div>
            <br/>
            <div class="row justify-content-between">
                <button (click)="addAdverbData()" class="col-3 btn btn-form btn-primary disabled">Add adverb data</button>
                <button (click)="saveAdverbAndClose()" class="col-3 btn btn-form btn-success">Save and Close</button>
                <button (click)="gotoAdverbs()" class="col-3 btn btn-form btn-danger">CLOSE</button>
            </div>
        </div>
    </div>
  `,
  animations: [ slideInDownAnimation ]
})
export class AdverbDetailComponent implements OnInit {
  @HostBinding('@routeAnimation') routeAnimation = true;

  adverb: Adverb;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: AdverbService,
    private alertService: AlertService
  ) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) =>
        this.service.getAdverbById(params.get('id')))
      .subscribe((adverb: Adverb) => this.adverb = adverb);
  }

  gotoAdverbs() {
    let adverbId = this.adverb ? this.adverb.id : null;
    this.router.navigate(['/adverbs']);
  }

  addAdverbData(){
    let adverbData = new AdverbData();
    this.adverb.adverbDataCollection.push(adverbData);
  }

  saveAdverbAndClose(){
    this.service.saveAdverb(this.adverb).subscribe(
        (data) => console.log("save adverb with id "+data),
        (error) => {this.alertService.error("Error during saving adverb", true)}
        );
    this.gotoAdverbs();
  }
}