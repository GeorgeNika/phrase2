import { Observable }                       from 'rxjs/Observable';
import { Component, OnInit, OnDestroy }     from '@angular/core';
import { HttpErrorResponse }                from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { NounService}                       from './noun.service';
import { NounInfo }                         from '../model/noun/noun_info.class';
import { PageParameters }                   from '../model/page_parameters.class';
import { AlertService }                     from '../useful/alert/alert.service';

@Component({
  template: `
    <div class="container-fluid">
        <h2>Nouns</h2>
    </div>
    <div class="container-fluid">
        <div class="row justify-content-between">
            <button (click)="addNoun()" class="col-2 btn btn-success">+ Add noun</button>
            <div class="col-8">
                <input [(ngModel)]="searchString" (change)="onChangeSearch()" class="col-4" placeholder="search"/>
                <button (click)="onChangeSearch()" class="col-2 btn btn-success"> Search </button>
                <button (click)="clearFilter()" class="col-2 btn btn-success"> Clear </button>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <table class="table table-striped table-hover">
            <thead>
                <tr class="row bg-success">
                    <td class="col-1">id</td>
                    <td class="col-3">russian</td>
                    <td class="col-1">forms</td>
                    <td class="col-3">actions</td>
                    <td class="col-3 text-right">hebrew</td>
                    <td class="col-1 text-right">id</td>
                </tr> 
            </thead>
            <tbody>
                <tr *ngFor="let nounInfo of nounInfoList" class="row align-items-center"
                    [routerLink]="['/noun', nounInfo.id]">
                    <th class="col-1 border-0" scope="row">{{ nounInfo.id }}</th>
                    <td class="col-3 border-0">{{ nounInfo.russian }}</td>
                    <td class="col-1 border-0">{{ nounInfo.childQuantity }}</td>
                    <td class="col-3 border-0">            
                            <button [routerLink]="['/noun', nounInfo.id]" class="btn btn-sm btn-warning"> Edit </button>
                    </td>
                    <td class="col-3 border-0 text-right">{{ nounInfo.hebrew }}</td>
                    <th class="col-1 border-0 text-right" scope="row">{{ nounInfo.id }}</th>
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
export class NounListComponent implements OnInit {
  nounInfoList : NounInfo[] = [];

  searchString: string = "";
  currentPage = 1;
  itemsOnPage = 15;
  totalPages = 0;

  constructor(private service: NounService, private alertService: AlertService, private router: Router) {}

  ngOnInit() {
      let previousPageParameters = this.service.getNounPageParameters();
      if (previousPageParameters ) {
          this.searchString = previousPageParameters.searchString;
          this.getNounsInfoList(  previousPageParameters.currentPage );
      } else {
          this.getNounsInfoList( 1 );
      }
  }

  ngOnDestroy() {
      let pageParameters = new PageParameters;
      pageParameters.searchString =this.searchString;
      pageParameters.currentPage = this.currentPage;
      this.service.saveNounPageParameters(pageParameters);
  }

  getNounsInfoList(page: number){
    this.service.getNounInfoList(page, this.itemsOnPage, this.searchString)
        .subscribe(val => this.setDataInfo(val));
  }

  setDataInfo(val: object){
    this.currentPage = val.currentPage;
    this.itemsOnPage = val.itemsOnPage;
    this.totalPages = val.totalPages;
    this.nounInfoList = JSON.parse(val.jsonContent);
  }

  addNoun(){
      this.router.navigate(['/noun', 0]);
  }

  clearFilter(){
      this.searchString="";
      this.onChangeSearch();
  }

  onChangeSearch(){
    this.getNounsInfoList(1);
  }

  goToPage(n: number): void {
    this.getNounsInfoList(n);
  }

  onNext(): void {
    this.getNounsInfoList(this.currentPage + 1);
  }

  onPrev(): void {
    this.getNounsInfoList(this.currentPage - 1);
  }

}
