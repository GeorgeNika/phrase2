import { LanguageUnit }             from '../language_unit.class';

export class NounData{
    id: number;
    genderRU: number;
    quantityRU: number;
    genderIL: number;
    quantityIL: number;
    languageUnit: LanguageUnit;

    constructor(){
        this.languageUnit = new LanguageUnit();
    }
}
