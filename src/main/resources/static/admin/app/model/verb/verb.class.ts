import { LanguageUnit }             from '../language_unit.class';
import { VerbData }                 from './verb_data.class';

export class Verb{
    id: number;
    infinitive : LanguageUnit;
    preposition : LanguageUnit;
    verbDataCollection: VerbData[];

    constructor (){
        this.id = 0;
        this.infinitive = new LanguageUnit();
        this.preposition = new LanguageUnit();
        this.verbDataCollection = [];
    }
}