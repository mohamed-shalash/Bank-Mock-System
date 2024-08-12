import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ATMComponent } from './atm.component';

describe('ATMComponent', () => {
  let component: ATMComponent;
  let fixture: ComponentFixture<ATMComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ATMComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ATMComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
