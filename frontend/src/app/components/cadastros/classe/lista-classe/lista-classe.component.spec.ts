import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaClasseComponent } from './lista-classe.component';

describe('ListaClasseComponent', () => {
  let component: ListaClasseComponent;
  let fixture: ComponentFixture<ListaClasseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaClasseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaClasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
