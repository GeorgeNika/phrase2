import { Observable }                       from 'rxjs/Observable';
import { Component, OnInit, OnDestroy }     from '@angular/core';
import { HttpErrorResponse }                from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { AdjectiveService}                  from './adjective.service';
import { AdjectiveInfo }                    from '../model/adjective/adjective_info.class';
import { PageParameters }                   from '../model/page_parameters.class';
import { AlertService }                     from '../useful/alert/alert.service';

@Component({
  template: `
    <div class="container-fluid">
        <h2>Adjectives</h2>
    </div>
    <div class="container-fluid">
        <div class="row justify-content-between">
            <button (click)="addAdjective()" class="col-2 btn btn-secondary">+ Add adjective</button>
            <div class="col-8">
                <input [(ngModel)]="searchString" (change)="onChangeSearch()" class="col-4" placeholder="search"/>
                <button (click)="onChangeSearch()" class="col-2 btn btn-secondary"> Search </button>
                <button (click)="clearFilter()" class="col-2 btn btn-secondary"> Clear </button>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <table class="table table-striped table-hover">
            <thead>
                <tr class="row bg-secondary">
                    <td class="col-1">id</td>
                    <td class="col-3">russian</td>
                    <td class="col-1">forms</td>
                    <td class="col-3">actions</td>
                    <td class="col-3 text-right">hebrew</td>
                    <td class="col-1 text-right">id</td>
                </tr> 
            </thead>
            <tbody>
                <tr *ngFor="let adjectiveInfo of adjectiveInfoList" class="row align-items-center"
                    [routerLink]="['/adjective', adjectiveInfo.id]">
                    <th class="col-1 border-0" scope="row">{{ adjectiveInfo.id }}</th>
                    <td class="col-3 border-0">{{ adjectiveInfo.russian }}</td>
                    <td class="col-1 border-0">{{ adjectiveInfo.childQuantity }}</td>
                    <td class="col-3 border-0">            
                            <button [routerLink]="['/adjective', adjectiveInfo.id]" class="btn btn-sm btn-warning"> Edit </button>
                    </td>
                    <td class="col-3 border-0 text-right">{{ adjectiveInfo.hebrew }}</td>
                    <th class="col-1 border-0 text-right" scope="row">{{ adjectiveInfo.id }}</th>
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
export class AdjectiveListComponent implements OnInit {
  adjectiveInfoList : AdjectiveInfo[] = [];

  searchString: string = "";
  currentPage = 1;
  itemsOnPage = 15;
  totalPages = 0;

  constructor(private service: AdjectiveService, private alertService: AlertService, private router: Router) {}

  ngOnInit() {
      let previousPageParameters = this.service.getAdjectivePageParameters();
      if (previousPageParameters ) {
          this.searchString = previousPageParameters.searchString;
          this.getAdjectivesInfoList(  previousPageParameters.currentPage );
      } else {
          this.getAdjectivesInfoList( 1 );
      }
  }

  ngOnDestroy() {
      let pageParameters = new PageParameters;
      pageParameters.searchString =this.searchString;
      pageParameters.currentPage = this.currentPage;
      this.service.saveAdjectivePageParameters(pageParameters);
  }

  getAdjectivesInfoList(page: number){
    this.service.getAdjectiveInfoList(page, this.itemsOnPage, this.searchString)
        .subscribe(val => this.setDataInfo(val));
  }

  setDataInfo(val: object){
    this.currentPage = val.currentPage;
    this.itemsOnPage = val.itemsOnPage;
    this.totalPages = val.totalPages;
    this.adjectiveInfoList = JSON.parse(val.jsonContent);
  }

  addAdjective(){
      this.router.navigate(['/adjective', 0]);
  }

  clearFilter(){
      this.searchString="";
      this.onChangeSearch();
  }

  onChangeSearch(){
    this.getAdjectivesInfoList(1);
  }

  goToPage(n: number): void {
    this.getAdjectivesInfoList(n);
  }

  onNext(): void {
    this.getAdjectivesInfoList(this.currentPage + 1);
  }

  onPrev(): void {
    this.getAdjectivesInfoList(this.currentPage - 1);
  }

}
