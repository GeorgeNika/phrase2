import { Component, Input, Output, EventEmitter } from '@angular/core';

const timeValueConst = [
    {name: "past", value: 1},
    {name: "present", value: 2},
    {name: "future", value: 3}
]
@Component({
    selector: 'selectTimeComponent',
    template:`
        <select [(ngModel)]="time" (change)="onChangeValue()">
            <option *ngFor="let sel of timeValues" [ngValue]="sel.value">{{sel.name}}</option>
        </select>
    `
})

export class SelectTimeComponent {

    @Input('time') time: number;
    @Output() timeChange = new EventEmitter<number>();

    timeValues = timeValueConst;

    onChangeValue(){
        this.timeChange.emit(this.time);
    }
}