import { Component, Input, Output, EventEmitter } from '@angular/core';

const genderValueConst = [
    {name: "муж", value: 1},
    {name: "жен", value: 2}
]
@Component({
    selector: 'selectGenderComponent',
    template:`
        <select [(ngModel)]="gender" (change)="onChangeValue()">
            <option *ngFor="let sel of genderValues" [ngValue]="sel.value">{{sel.name}}</option>
        </select>
    `
})

export class SelectGenderComponent {

    @Input('gender') gender: number;
    @Output() genderChange = new EventEmitter<number>();

    genderValues = genderValueConst;

    onChangeValue(){
        this.genderChange.emit(this.gender);
    }
}