import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { SelectPersonComponent }      from "./select-person.component";

@NgModule({
    imports:[CommonModule,
        FormsModule],
    declarations:[SelectPersonComponent],
    exports:[SelectPersonComponent]
})

export class SelectPersonModule {}
