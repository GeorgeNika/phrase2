import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { SelectPronounComponent }      from "./select-pronoun.component";

@NgModule({
    imports:[CommonModule,
        FormsModule],
    declarations:[SelectPronounComponent],
    exports:[SelectPronounComponent]
})

export class SelectPronounModule {}
