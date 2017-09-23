import { Component, Input, Output, EventEmitter } from '@angular/core';

export class Pronoun {
    person: number;
    gender: number;
    quantity: number;

    constructor(person: number, gender: number, quantity: number){
        this.person = person;
        this.gender = gender;
        this.quantity = quantity;
    }
}

const pronounValueConst = [
    {name: "Я(м)",  id: 1, value: new Pronoun(1,1,1)},
    {name: "Я(ж)",  id: 2, value: new Pronoun(1,2,1)},
    {name: "Мы",    id: 3, value: new Pronoun(1,1,2)},
    {name: "Ты(м)", id: 4, value: new Pronoun(2,1,1)},
    {name: "Ты(ж)", id: 5, value: new Pronoun(2,2,1)},
    {name: "Вы",    id: 6, value: new Pronoun(2,1,2)},
    {name: "Он",    id: 7, value: new Pronoun(3,1,1)},
    {name: "Она",   id: 8, value: new Pronoun(3,2,1)},
    {name: "Они",   id: 9, value: new Pronoun(3,1,2)}
]

@Component({
    selector: 'selectPronounComponent',
    template:`
        <select [(ngModel)]="pronounId" (change)="onChangeValue()" >
            <option *ngFor="let sel of pronounValues" [ngValue]="sel.id">{{sel.name}}</option>
        </select>
    `
})

export class SelectPronounComponent {

    @Output() pronounChange = new EventEmitter<Pronoun>();

    @Input()
    set pronoun(pronoun: Pronoun) {
        this.pronounId = this.findIdByPronoun(pronoun);
    }

    pronounId : number;

    pronounValues = pronounValueConst;

    onChangeValue(){
        this.pronounChange.emit(this.findPronounById(this.pronounId));
    }

    findIdByPronoun(pronoun :Pronoun){
        for (var loopId in pronounValueConst) {
            var loopPronounValue = pronounValueConst[loopId].value;
            if (loopPronounValue.person == pronoun.person) {
                if (loopPronounValue.gender == pronoun.gender){
                    if (loopPronounValue.quantity == pronoun.quantity){
                        return pronounValueConst[loopId].id;
                    }
                }
            }
        }
        return 0;
    }

    findPronounById(id: number){
        for (var loopId in pronounValueConst) {
            if (pronounValueConst[loopId].id == id){
                return pronounValueConst[loopId].value;
            }
        }
        return null;
    }
}