import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AdverbListComponent }    from './adverb-list.component';
import { AdverbDetailComponent }  from './adverb-detail.component';

const adverbsRoutes: Routes = [
  { path: 'adverbs',  component: AdverbListComponent },
  { path: 'adverb/:id', component: AdverbDetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(adverbsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdverbRoutingModule { }


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/