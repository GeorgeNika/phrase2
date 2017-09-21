import { Component } from '@angular/core';

@Component({
  selector: 'admin',
  template: `
    <h1 class="title">Angular Router</h1>
    <nav>
      <a routerLink="/verbs" routerLinkActive="active">Verbs</a>
    </nav>
    <router-outlet></router-outlet>
    <router-outlet name="popup"></router-outlet>
  `
})
export class AppComponent {
}


/*
Copyright 2017 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/