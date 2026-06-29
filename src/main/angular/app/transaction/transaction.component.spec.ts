import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionComponent } from './transaction.component';

describe('TransactionComponent', () => {
    let component: TransactionComponent;
    let fixture: ComponentFixture<TransactionComponent>;

    beforeEach(() => {
        fixture = TestBed.createComponent(TransactionComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should compile', () => {
        expect(component).toBeTruthy();
    });
});
