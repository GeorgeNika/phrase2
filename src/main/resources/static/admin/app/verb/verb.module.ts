import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { VerbListComponent }    from './verb-list.component';
import { VerbDetailComponent }  from './verb-detail.component';

import { VerbService } from './verb.service';

import { VerbRoutingModule } from './verb-routing.module';
import { SelectTimeComponent } from "../useful/select-time.component";
import { SelectPersonComponent } from "../useful/select-person.component";
import { SelectQuantityComponent } from "../useful/select-quantity.component";
import { SelectGenderComponent } from "../useful/select-gender.component";
import { SelectPronounComponent } from "../useful/select-pronoun.component";

@NgModule({
  imports: [
      CommonModule,
      FormsModule,
      VerbRoutingModule
  ],
  declarations: [
      VerbListComponent,
      VerbDetailComponent,
      SelectTimeComponent,
      SelectPersonComponent,
      SelectQuantityComponent,
      SelectPronounComponent,
      SelectGenderComponent
  ],
  providers: [ VerbService ]
})
export class VerbModule {}