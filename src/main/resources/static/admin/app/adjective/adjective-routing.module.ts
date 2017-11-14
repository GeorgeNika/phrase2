import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdjectiveListComponent }    from './adjective-list.component';
import { AdjectiveDetailComponent }  from './adjective-detail.component';

const adjectivesRoutes: Routes = [
  { path: 'adjectives',  component: AdjectiveListComponent },
  { path: 'adjective/:id', component: AdjectiveDetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(adjectivesRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdjectiveRoutingModule { }


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/