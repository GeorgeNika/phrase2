import { Observable }                       from 'rxjs/Observable';
import { Component, OnInit, OnDestroy }     from '@angular/core';
import { HttpErrorResponse }                from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { VerbService }                      from './verb.service';
import { VerbInfo }                         from '../model/verb/verb_info.class';
import { AlertService }                     from '../useful/alert/alert.service';
import {PageParameters} from "../model/page_parameters.class";

@Component({
  template: `
    <div class="container-fluid">
        <h2>Verbs</h2>
    </div>
    <div class="container-fluid">
        <div class="row justify-content-between">
            <button (click)="addVerb()" class="col-2 btn btn-info">+ Add verb</button>
            <div class="col-8">
                <input [(ngModel)]="searchString" (change)="onChangeSearch()" class="col-4" placeholder="search"/>
                <button (click)="onChangeSearch()" class="col-2 btn btn-info"> Search </button>
                <button (click)="clearFilter()" class="col-2 btn btn-info"> Clear </button>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <table class="table table-striped table-hover">
            <thead>
                <tr class="row bg-info">
                    <td class="col-1">id</td>
                    <td class="col-3">russian</td>
                    <td class="col-1">forms</td>
                    <td class="col-3">actions</td>
                    <td class="col-3 text-right">hebrew</td>
                    <td class="col-1 text-right">id</td>
                </tr> 
            </thead>
            <tbody>
                <tr *ngFor="let verbInfo of verbInfoList" class="row align-items-center"
                    [routerLink]="['/verb', verbInfo.id]">
                    <th class="col-1 border-0">{{ verbInfo.id }}</th>
                    <td class="col-3 border-0">{{ verbInfo.russian }}</td>
                    <td class="col-1 border-0">{{ verbInfo.childQuantity }}</td>
                    <td class="col-3 border-0">            
                            <button [routerLink]="['/verb', verbInfo.id]" class="btn btn-sm btn-warning"> Edit </button>
                            <button class="btn btn-sm" 
                                    (click)="onClickActionVerb($event, verbInfo)"
                                    [ngClass]="verbInfo.actionVerb ? 'btn-danger' : 'btn-success'">
                                {{ verbInfo.actionVerb ? '-' : '+' }} action
                            </button>
                    </td>
                    <td class="col-3 border-0 text-right">{{ verbInfo.hebrew }}</td>
                    <th class="col-1 border-0 text-right">{{ verbInfo.id }}</th>
                </tr>
            </tbody>
        </table>
    </div>
    <pagination
        (goPage)="goToPage($event)"
        (goNext)="onNext()"
        (goPrev)="onPrev()"
        [pagesToShow]="6"
        [currentPage]="currentPage"
        [itemsOnPage]="itemsOnPage"
        [totalPages]="totalPages">
    </pagination>
  `
})
export class VerbListComponent implements OnInit {
  verbInfoList : VerbInfo[] = [];

  searchString: string = "";
  currentPage = 1;
  itemsOnPage = 15;
  totalPages = 0;

  constructor(private service: VerbService, private alertService: AlertService, private router: Router) {}

  ngOnInit() {
      let previousPageParameters = this.service.getVerbPageParameters();
      if (previousPageParameters ) {
          this.searchString = previousPageParameters.searchString;
          this.getVerbsInfoList(  previousPageParameters.currentPage );
      } else {
          this.getVerbsInfoList( 1 );
      }
  }

  ngOnDestroy() {
      let pageParameters = new PageParameters;
      pageParameters.searchString =this.searchString;
      pageParameters.currentPage = this.currentPage;
      this.service.saveVerbPageParameters(pageParameters);
  }

  getVerbsInfoList(page: number){
    this.service.getVerbInfoList(page, this.itemsOnPage, this.searchString)
        .subscribe(val => this.setDataInfo(val));
  }

  setDataInfo(val: object){
    this.currentPage = val.currentPage;
    this.itemsOnPage = val.itemsOnPage;
    this.totalPages = val.totalPages;
    this.verbInfoList = JSON.parse(val.jsonContent);
  }

  addVerb(){
      this.router.navigate(['/verb', 0]);
  }

  clearFilter(){
      this.searchString="";
      this.onChangeSearch();
  }

  onClickActionVerb(event, verbInfo: VerbInfo)
  {
      event.stopPropagation();
      this.service.changeActionVerb(verbInfo.id)
          .subscribe(
              (status: boolean) => verbInfo.actionVerb = status,
              (error) => { this.alertService.error("Error during change 'action verb' status")}
          );
  }

  onChangeSearch(){
    this.getVerbsInfoList(1);
  }

  goToPage(n: number): void {
    this.getVerbsInfoList(n);
  }

  onNext(): void {
    this.getVerbsInfoList(this.currentPage + 1);
  }

  onPrev(): void {
    this.getVerbsInfoList(this.currentPage - 1);
  }

}
