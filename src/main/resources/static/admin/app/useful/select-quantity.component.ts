import { Component, Input, Output, EventEmitter } from '@angular/core';

const quantityValueConst = [
    {name: "один", value: 1},
    {name: "много", value: 2}
]
@Component({
    selector: 'selectQuantityComponent',
    template:`
        <select [(ngModel)]="quantity" (change)="onChangeValue()">
            <option *ngFor="let sel of quantityValues" [ngValue]="sel.value">{{sel.name}}</option>
        </select>
    `
})

export class SelectQuantityComponent {

    @Input('quantity') quantity: number;
    @Output() quantityChange = new EventEmitter<number>();

    quantityValues = quantityValueConst;

    onChangeValue(){
        this.quantityChange.emit(this.quantity);
    }
}