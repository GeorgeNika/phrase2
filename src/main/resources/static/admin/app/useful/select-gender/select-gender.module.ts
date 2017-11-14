import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { SelectGenderComponent }      from "./select-gender.component";

@NgModule({
    imports:[CommonModule,
        FormsModule],
    declarations:[SelectGenderComponent],
    exports:[SelectGenderComponent]
})

export class SelectGenderModule {}
