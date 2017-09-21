import { NgModule }                 from '@angular/core';
import { BrowserModule }            from '@angular/platform-browser';
import { HttpClientModule }         from '@angular/common/http';
import { FormsModule }              from '@angular/forms';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';

import { Router } from '@angular/router';

import { AppComponent }            from './app.component';
import { AppRoutingModule }        from './app-routing.module';

import { VerbModule }              from './verb/verb.module';

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    VerbModule,
    AppRoutingModule,
    BrowserAnimationsModule
  ],
  declarations: [
    AppComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}