import { LanguageUnit }             from '../language_unit.class';

export class Adverb{
    id: number;
    mainForm : LanguageUnit;

    constructor (){
        this.id = 0;
        this.mainForm = new LanguageUnit();
    }
}
