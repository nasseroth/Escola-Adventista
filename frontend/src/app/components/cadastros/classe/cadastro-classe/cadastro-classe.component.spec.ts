import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroClasseComponent } from './cadastro-classe.component';

describe('CadastroClasseComponent', () => {
  let component: CadastroClasseComponent;
  let fixture: ComponentFixture<CadastroClasseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroClasseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroClasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
