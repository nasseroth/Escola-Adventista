import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CadastrosRoutingModule } from './cadastros-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule, MatSnackBarModule, MatSelectModule, MatCardModule } from '@angular/material';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { UsuarioComponent } from './usuario/usuario.component';
import { CadastroUsuarioComponent } from './usuario/cadastro-usuario/cadastro-usuario.component';
import { ListaUsuarioComponent } from './usuario/lista-usuario/lista-usuario.component';
import { ClasseComponent } from './classe/classe.component';
import { CadastroClasseComponent } from './classe/cadastro-classe/cadastro-classe.component';
import { ListaClasseComponent } from './classe/lista-classe/lista-classe.component';
import { AlunoComponent } from './aluno/aluno.component';
import { CadastroAlunoComponent } from './aluno/cadastro-aluno/cadastro-aluno.component';
import { ListaAlunoComponent } from './aluno/lista-aluno/lista-aluno.component';

@NgModule({
  declarations: [
    UsuarioComponent,
    CadastroUsuarioComponent,
    ListaUsuarioComponent,
    ClasseComponent,
    CadastroClasseComponent,
    ListaClasseComponent,
    AlunoComponent,
    CadastroAlunoComponent,
    ListaAlunoComponent,
  ],
  imports: [
    CommonModule,
    CadastrosRoutingModule,
    BrowserModule,
    ReactiveFormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    HttpClientModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCardModule
  ]
})
export class CadastrosModule { }
