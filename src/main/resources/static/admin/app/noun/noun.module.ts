import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { NounListComponent }    from './noun-list.component';
import { NounDetailComponent }  from './noun-detail.component';

import { NounService } from './noun.service';

import { NounRoutingModule }        from './noun-routing.module';
import {SelectGenderModule} from "../useful/select-gender/select-gender.module";
import {SelectQuantityModule} from "../useful/select-quantity/select-quantity.module";
import {PaginationModule} from "../useful/pagination/pagination.module";

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      NounRoutingModule,
      SelectGenderModule,
      SelectQuantityModule,
      PaginationModule
  ],
  declarations: [
      NounListComponent,
      NounDetailComponent
  ],
  providers: [ NounService ]
})
export class NounModule {}