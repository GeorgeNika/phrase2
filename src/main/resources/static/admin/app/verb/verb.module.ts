import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { VerbListComponent }    from './verb-list.component';
import { VerbDetailComponent }  from './verb-detail.component';

import { VerbService } from './verb.service';

import { VerbRoutingModule }        from './verb-routing.module';

import {SelectTimeModule} from "../useful/select-time/select-time.module";
import {SelectPronounModule} from "../useful/select-pronoun/select-pronoun.module";
import {PaginationModule} from "../useful/pagination/pagination.module";

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      VerbRoutingModule,
      SelectTimeModule,
      SelectPronounModule,
      PaginationModule
  ],
  declarations: [
      VerbListComponent,
      VerbDetailComponent
  ],
  providers: [ VerbService ]
})
export class VerbModule {}