import { Component, Input, Output, EventEmitter } from '@angular/core';

const personValueConst = [
    {name: "i - we", value: 1},
    {name: "you", value: 2},
    {name: "he she they", value: 3}
]
@Component({
    selector: 'selectPersonComponent',
    template:`
        <select [(ngModel)]="person" (change)="onChangeValue()">
            <option *ngFor="let sel of personValues" [ngValue]="sel.value">{{sel.name}}</option>
        </select>
    `
})

export class SelectPersonComponent {

    @Input('person') person: number;
    @Output() personChange = new EventEmitter<number>();

    personValues = personValueConst;

    onChangeValue(){
        this.personChange.emit(this.person);
    }
}