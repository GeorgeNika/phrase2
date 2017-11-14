import { LanguageUnit }             from '../language_unit.class';

export class AdjectiveData{
    id: number;
    gender: number;
    quantity: number;
    languageUnit: LanguageUnit;

    constructor(){
        this.languageUnit = new LanguageUnit();
    }
}
