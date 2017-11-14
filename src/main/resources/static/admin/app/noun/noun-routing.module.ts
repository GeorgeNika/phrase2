import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { NounListComponent }    from './noun-list.component';
import { NounDetailComponent }  from './noun-detail.component';

const nounsRoutes: Routes = [
  { path: 'nouns',  component: NounListComponent },
  { path: 'noun/:id', component: NounDetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(nounsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class NounRoutingModule { }


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/