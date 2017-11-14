export class  VerbInfo {
    id: number;
    hebrew: string;
    russian: string;
    actionVerb: boolean;
    childQuantity: number;


    constructor (){
        this.id = 0;
        this.hebrew = '';
        this.russian = '';
        this.actionVerb = false;
        this.childQuantity = 0;
    }
}
