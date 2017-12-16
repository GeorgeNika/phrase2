import { Component } from '@angular/core';

@Component({
  selector: 'admin',
  template: `
    <nav>
      <a routerLink="/verbs" routerLinkActive="active" class="btn btn-sm btn-info">verbs</a>
      <span>  </span>
      <a routerLink="/nouns" class="btn btn-sm btn-success" >nouns</a>
      <span>  </span>
      <a routerLink="/adjectives" class="btn btn-sm btn-secondary" >adjectives</a>
      <span>  </span>
      <a routerLink="/adverbs" class="btn btn-sm btn-warning" >adverbs</a>
      <span>  </span>
      <a href="/password/logout" class="btn btn-sm btn-danger">logout</a>
    </nav>
    <alert></alert>
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