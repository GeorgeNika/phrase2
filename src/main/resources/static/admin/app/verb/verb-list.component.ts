import { Observable } from 'rxjs/Observable';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import {VerbInfo, VerbService}  from './verb.service';

@Component({
  template: `
    <div class="container-fluid">
        <h2>Verbs</h2>
        <button (click)="addVerb()" class="col-2 btn btn-success"> ADD VERB</button>
        <input [(ngModel)]="searchString" (change)="onChangeSearch()" class="col-3" placeholder="search"/>
    </div>
    <div class="container-fluid">
        <table class="table container-fluid table-striped table-hover">
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
                <tr *ngFor="let verbInfo of visibleVerbInfoList" class="row"
                    [routerLink]="['/verb', verbInfo.id]">
                    <th class="col-1" scope="row">{{ verbInfo.id }}</th>
                    <td class="col-3">{{ verbInfo.russian }}</td>
                    <td class="col-1">{{ verbInfo.childQuantity }}</td>
                    <td class="col-3">            
                            <button [routerLink]="['/verb', verbInfo.id]" class="btn btn-warning"> Edit </button>
                            <button class="btn" 
                                    (click)="onClickActionVerb($event, verbInfo)"
                                    [ngClass]="verbInfo.actionVerb ? 'btn-danger' : 'btn-success'">
                                {{ verbInfo.actionVerb ? 'Remove ' : 'Add ' }} action verb
                            </button>
                    </td>
                    <td class="col-3 text-right">{{ verbInfo.hebrew }}</td>
                    <th class="col-1 text-right" scope="row">{{ verbInfo.id }}</th>
                </tr>
            </tbody>
        </table>
    </div>
  `
})
export class VerbListComponent implements OnInit {
  verbInfoList : VerbInfo[];
  visibleVerbInfoList: VerbInfo[];
  searchString: string;

  constructor(private service: VerbService) {}

  ngOnInit() {
    this.service.getVerbInfoList().subscribe(val => this.visibleVerbInfoList = this.verbInfoList = val);
  }

  addVerb(){
    let verbInfo = new VerbInfo();
    this.verbInfoList.push(verbInfo);
  }

  onClickActionVerb(event, verbInfo: VerbInfo)
  {
    event.stopPropagation();
    console.log(verbInfo);
    this.service.changeActionVerb(verbInfo.id)
      .subscribe((status: boolean) => verbInfo.actionVerb = status);
  }

  onChangeSearch(){
    if (this.searchString == '') {
      this.visibleVerbInfoList = this.verbInfoList;
    }else {
      this.visibleVerbInfoList = this.verbInfoList.filter(this.filterForVerbInfo,this);
    }
  }

  filterForVerbInfo(verbInfo){
    if (verbInfo.hebrew.indexOf(this.searchString) >= 0){
      return true;
    }
    if (verbInfo.russian.indexOf(this.searchString) >= 0){
      return true;
    }
    return false;
  }
}
