import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { SelectTimeComponent }      from "./select-time.component";

@NgModule({
    imports:[CommonModule,
        FormsModule],
    declarations:[SelectTimeComponent],
    exports:[SelectTimeComponent]
})

export class SelectTimeModule {}
