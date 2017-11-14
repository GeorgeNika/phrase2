import { AdjectiveData }                                 from './adjective_data.class';

export class Adjective{
    id: number;
    adjectiveDataCollection: AdjectiveData[];

    constructor (){
        this.id = 0;
        this.adjectiveDataCollection = [];
    }
}
