import { CadastroUsuarioComponent } from './components/cadastros/usuario/cadastro-usuario/cadastro-usuario.component';
import { ListaUsuarioComponent } from './components/cadastros/usuario/lista-usuario/lista-usuario.component';
import { UsuarioComponent } from './components/cadastros/usuario/usuario.component';
import { HomeComponent } from './components/home/home.component';
import { CadastroClasseComponent } from './components/cadastros/classe/cadastro-classe/cadastro-classe.component';

import { NgModule, ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule, ROUTES } from '@angular/router';
import { ListaClasseComponent } from './components/cadastros/classe/lista-classe/lista-classe.component';

import { ClasseComponent } from './components/cadastros/classe/classe.component';
import { AuthGuard } from './components/security/auth.guard';
import { LoginComponent } from './components/security/login/login.component';
import { ListaAlunoComponent } from './components/cadastros/aluno/lista-aluno/lista-aluno.component';
import { AlunoComponent } from './components/cadastros/aluno/aluno.component';
import { CadastroAlunoComponent } from './components/cadastros/aluno/cadastro-aluno/cadastro-aluno.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full', canActivate: [AuthGuard] },
  { path : 'login', component: LoginComponent },
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  {path: 'classe', component: ClasseComponent, canActivate: [AuthGuard], children: [
    {path: 'listar', component: ListaClasseComponent, canActivate: [AuthGuard] },
    {path: 'novo', component: CadastroClasseComponent, canActivate: [AuthGuard] },
    {path: 'novo/:id', component: CadastroClasseComponent, canActivate: [AuthGuard] },
    {path: '', redirectTo: 'listar', pathMatch: 'full', canActivate: [AuthGuard] }
  ]},
  {path: 'aluno', component: AlunoComponent, canActivate: [AuthGuard], children: [
    {path: 'listar', component: ListaAlunoComponent, canActivate: [AuthGuard] },
    {path: 'novo', component: CadastroAlunoComponent, canActivate: [AuthGuard] },
    {path: 'novo/:id', component: CadastroAlunoComponent, canActivate: [AuthGuard] },
    {path: '', redirectTo: 'listar', pathMatch: 'full', canActivate: [AuthGuard] }
  ]},
  {path: 'usuario', component: UsuarioComponent, canActivate: [AuthGuard], children: [
    {path: 'listar', component: ListaUsuarioComponent, canActivate: [AuthGuard] },
    {path: 'novo', component: CadastroUsuarioComponent, canActivate: [AuthGuard] },
    {path: 'novo/:id', component: CadastroUsuarioComponent, canActivate: [AuthGuard] },
    {path: '', redirectTo: 'listar', pathMatch: 'full', canActivate: [AuthGuard] }
  ]},
  {path: '**', redirectTo: 'login', pathMatch: 'full' }
];

// export const routes: ModuleWithProviders = RouterModule.forRoot(ROUTES);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
