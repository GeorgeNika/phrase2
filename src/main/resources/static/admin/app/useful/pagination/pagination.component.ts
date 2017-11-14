// based on http://www.bentedder.com/create-a-pagination-component-in-angular-4/

import { Component, Input, EventEmitter, Output } from '@angular/core';

@Component({
    selector: 'pagination',
    template: `
        <nav aria-label="Page navigation">
            <ul class="pagination" *ngIf="totalPages > 0">
                <li class="page-item" [class.disabled]="currentPage === 1">
                    <button (click)="onFirst()" class="page-link" > First </button>
                </li>
                <li class="page-item" [class.disabled]="currentPage === 1">
                    <button (click)="onPrev()" class="page-link"  > Previous </button>
                </li>
                <li class="page-item" *ngFor="let pageNum of getPages()" [class.active]="pageNum == currentPage">
                    <button (click)="onPage(pageNum)" class="page-link"> {{pageNum}} </button>
                </li>
                <li class="page-item" [class.disabled]="currentPage === totalPages">
                    <button (click)="onNext()" class="page-link" > Next </button>
                </li>
                <li class="page-item" [class.disabled]="currentPage === totalPages">
                    <button (click)="onLast()" class="page-link" > Last </button>
                </li>
            </ul>
        </nav>
    `
})
export class PaginationComponent {
    @Input() currentPage: number;
    @Input() totalPages: number;
    @Input() itemsOnPage: number;
    @Input() pagesToShow: number;

    @Output() goPrev = new EventEmitter();
    @Output() goNext = new EventEmitter();
    @Output() goPage = new EventEmitter<number>();

    constructor() { }

    onPage(n: number): void {
        this.goPage.emit(n);
    }

    onPrev(): void {
        this.goPrev.emit();
    }

    onNext(): void {
        this.goNext.emit();
    }

    onLast(): void{
        this.onPage(this.totalPages);
    }

    onFirst(): void{
        this.onPage(1);
    }

    getPages(): number[]{
        let min = this.getMin();
        let max = this.getMax();
        const pages: number[] = [];
        for (let i = min; i <= max; i++){
            pages.push(i);
        }
        return pages;
    }

    getMin(): number {
        if (this.showLastPage()){
            return Math.max(1, this.totalPages - this.pagesToShow + 1);
        } else {
            return Math.max(1, this.currentPage - Math.floor(this.pagesToShow / 2) + 1);
        }
    }

    getMax(): number {
        return Math.min(this.totalPages, this.getMin() + this.pagesToShow - 1);
    }

    showLastPage(){
        if ((this.currentPage +  Math.floor(this.pagesToShow / 2)) < this.totalPages){
            return false;
        }
        return true;
    }
}