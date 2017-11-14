import { LanguageUnit }             from '../language_unit.class';

export class VerbData{
    id: number;
    gender: number;
    quantity: number;
    time: number;
    person: number;
    languageUnit: LanguageUnit;

    constructor(){
        this.languageUnit = new LanguageUnit();
    }
}