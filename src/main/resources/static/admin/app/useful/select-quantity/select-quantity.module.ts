import { NgModule }       from '@angular/core';
import { CommonModule }   from '@angular/common';
import { FormsModule }    from '@angular/forms';

import { SelectQuantityComponent }      from "./select-quantity.component";

@NgModule({
    imports:[CommonModule,
        FormsModule],
    declarations:[SelectQuantityComponent],
    exports:[SelectQuantityComponent]
})

export class SelectQuantityModule {}
