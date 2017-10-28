import { NgModule }                 from '@angular/core';
import { BrowserModule }            from '@angular/platform-browser';
import { HttpClientModule }         from '@angular/common/http';
import { FormsModule }              from '@angular/forms';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';

import { Router } from '@angular/router';

import { AppComponent }            from './app.component';
import { AppRoutingModule }        from './app-routing.module';

import { VerbModule }              from './verb/verb.module';
import { AlertComponent }          from './useful/alert/alert.component';
import { AlertService }            from './useful/alert/alert.service';

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
        AlertComponent
    ],
    providers: [
        AlertService
    ],
    bootstrap: [ AppComponent ]
})
export class AppModule {
    constructor(router: Router) {
    }
}