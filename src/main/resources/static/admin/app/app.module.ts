import { NgModule }                 from '@angular/core';
import { BrowserModule }            from '@angular/platform-browser';
import { HttpClientModule }         from '@angular/common/http';
import { FormsModule }              from '@angular/forms';
import { BrowserAnimationsModule }  from '@angular/platform-browser/animations';

import { Router } from '@angular/router';

import { AppComponent }             from './app.component';
import { AppRoutingModule }         from './app-routing.module';

import { VerbModule }               from './verb/verb.module';
import { NounModule }               from './noun/noun.module';
import { AdjectiveModule }          from './adjective/adjective.module';
import { AlertComponent }           from './useful/alert/alert.component';
import { AlertService }             from './useful/alert/alert.service';

import { SelectTimeModule }         from "./useful/select-time/select-time.module";
import { SelectPronounModule }      from "./useful/select-pronoun/select-pronoun.module";
import { SelectGenderModule }       from "./useful/select-gender/select-gender.module";
import { SelectPersonModule }       from "./useful/select-person/select-person.module";
import { SelectQuantityModule }     from "./useful/select-quantity/select-quantity.module";
import { PaginationModule }         from "./useful/pagination/pagination.module";

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule,
        VerbModule,
        NounModule,
        AdjectiveModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        SelectTimeModule,
        SelectGenderModule,
        SelectPersonModule,
        SelectQuantityModule,
        SelectPronounModule,
        PaginationModule
    ],
    declarations: [
        AppComponent,
        AlertComponent,

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