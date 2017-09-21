import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VerbListComponent }    from './verb-list.component';
import { VerbDetailComponent }  from './verb-detail.component';

const verbsRoutes: Routes = [
  { path: 'verbs',  component: VerbListComponent },
  { path: 'verb/:id', component: VerbDetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(verbsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class VerbRoutingModule { }


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/