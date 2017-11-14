import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { AdjectiveListComponent }       from './adjective-list.component';
import { AdjectiveDetailComponent }     from './adjective-detail.component';

import { AdjectiveService }             from './adjective.service';

import { AdjectiveRoutingModule }       from './adjective-routing.module';
import { SelectGenderModule }           from "../useful/select-gender/select-gender.module";
import { SelectQuantityModule }         from "../useful/select-quantity/select-quantity.module";
import { PaginationModule }             from "../useful/pagination/pagination.module";

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      AdjectiveRoutingModule,
      SelectGenderModule,
      SelectQuantityModule,
      PaginationModule
  ],
  declarations: [
      AdjectiveListComponent,
      AdjectiveDetailComponent
  ],
  providers: [ AdjectiveService ]
})
export class AdjectiveModule {}