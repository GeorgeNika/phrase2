import { Observable }                       from 'rxjs/Observable';
import { Component, OnInit, OnDestroy }     from '@angular/core';
import { HttpErrorResponse }                from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { AdverbService}                     from './adverb.service';
import { AdverbInfo }                       from '../model/adverb/adverb_info.class';
import { PageParameters }                   from '../model/page_parameters.class';
import { AlertService }                     from '../useful/alert/alert.service';

@Component({
  template: `
    <div class="container-fluid">
        <h2>Adverbs</h2>
    </div>
    <div class="container-fluid">
        <div class="row justify-content-between">
            <button (click)="addAdverb()" class="col-2 btn btn-warning">+ Add adverb</button>
            <div class="col-8">
                <input [(ngModel)]="searchString" (change)="onChangeSearch()" class="col-4" placeholder="search"/>
                <button (click)="onChangeSearch()" class="col-2 btn btn-warning"> Search </button>
                <button (click)="clearFilter()" class="col-2 btn btn-warning"> Clear </button>
            </div>
        </div>
    </div>
    <div class="container-fluid">
        <table class="table table-striped table-hover">
            <thead>
                <tr class="row bg-warning">
                    <td class="col-1">id</td>
                    <td class="col-3">russian</td>
                    <td class="col-4">actions</td>
                    <td class="col-3 text-right">hebrew</td>
                    <td class="col-1 text-right">id</td>
                </tr> 
            </thead>
            <tbody>
                <tr *ngFor="let adverbInfo of adverbInfoList" class="row align-items-center"
                    [routerLink]="['/adverb', adverbInfo.id]">
                    <th class="col-1 border-0" scope="row">{{ adverbInfo.id }}</th>
                    <td class="col-3 border-0">{{ adverbInfo.russian }}</td>
                    <td class="col-4 border-0">            
                            <button [routerLink]="['/adverb', adverbInfo.id]" class="btn btn-sm btn-warning"> Edit </button>
                    </td>
                    <td class="col-3 border-0 text-right">{{ adverbInfo.hebrew }}</td>
                    <th class="col-1 border-0 text-right" scope="row">{{ adverbInfo.id }}</th>
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
export class AdverbListComponent implements OnInit {
  adverbInfoList : AdverbInfo[] = [];

  searchString: string = "";
  currentPage = 1;
  itemsOnPage = 15;
  totalPages = 0;

  constructor(private service: AdverbService, private alertService: AlertService, private router: Router) {}

  ngOnInit() {
      let previousPageParameters = this.service.getAdverbPageParameters();
      if (previousPageParameters ) {
          this.searchString = previousPageParameters.searchString;
          this.getAdverbsInfoList(  previousPageParameters.currentPage );
      } else {
          this.getAdverbsInfoList( 1 );
      }
  }

  ngOnDestroy() {
      let pageParameters = new PageParameters;
      pageParameters.searchString =this.searchString;
      pageParameters.currentPage = this.currentPage;
      this.service.saveAdverbPageParameters(pageParameters);
  }

  getAdverbsInfoList(page: number){
    this.service.getAdverbInfoList(page, this.itemsOnPage, this.searchString)
        .subscribe(val => this.setDataInfo(val));
  }

  setDataInfo(val: object){
    this.currentPage = val.currentPage;
    this.itemsOnPage = val.itemsOnPage;
    this.totalPages = val.totalPages;
    this.adverbInfoList = JSON.parse(val.jsonContent);
  }

  addAdverb(){
      this.router.navigate(['/adverb', 0]);
  }

  clearFilter(){
      this.searchString="";
      this.onChangeSearch();
  }

  onChangeSearch(){
    this.getAdverbsInfoList(1);
  }

  goToPage(n: number): void {
    this.getAdverbsInfoList(n);
  }

  onNext(): void {
    this.getAdverbsInfoList(this.currentPage + 1);
  }

  onPrev(): void {
    this.getAdverbsInfoList(this.currentPage - 1);
  }

}
