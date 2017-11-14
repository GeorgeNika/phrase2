import { NounData }                                 from './noun_data.class';

export class Noun{
    id: number;
    nounDataCollection: NounData[];

    constructor (){
        this.id = 0;
        this.nounDataCollection = [];
    }
}
