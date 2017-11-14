import { LanguageUnit }             from '../language_unit.class';
import { VerbData }                 from './verb_data.class';

export class Verb{
    id: number;
    infinitive : LanguageUnit;
    verbDataCollection: VerbData[];

    constructor (){
        this.id = 0;
        this.infinitive = new LanguageUnit();
        this.verbDataCollection = [];
    }
}