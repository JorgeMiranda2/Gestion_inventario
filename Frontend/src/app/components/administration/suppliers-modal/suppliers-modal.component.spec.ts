import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuppliersModalComponent } from './suppliers-modal.component';

describe('SuppliersModalComponent', () => {
  let component: SuppliersModalComponent;
  let fixture: ComponentFixture<SuppliersModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuppliersModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SuppliersModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
