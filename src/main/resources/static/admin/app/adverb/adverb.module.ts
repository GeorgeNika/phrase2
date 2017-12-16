import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { AdverbListComponent }    from './adverb-list.component';
import { AdverbDetailComponent }  from './adverb-detail.component';

import { AdverbService }          from './adverb.service';

import { AdverbRoutingModule }    from './adverb-routing.module';
import { SelectGenderModule }     from "../useful/select-gender/select-gender.module";
import { SelectQuantityModule }   from "../useful/select-quantity/select-quantity.module";
import { PaginationModule }       from "../useful/pagination/pagination.module";

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      AdverbRoutingModule,
      SelectGenderModule,
      SelectQuantityModule,
      PaginationModule
  ],
  declarations: [
      AdverbListComponent,
      AdverbDetailComponent
  ],
  providers: [ AdverbService ]
})
export class AdverbModule {}