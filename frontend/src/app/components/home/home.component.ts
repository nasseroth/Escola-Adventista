import { MatSnackBar, MatTableDataSource } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { AlunoService } from 'src/app/services/aluno.service';
import { Observable } from 'rxjs';
import { ClasseService } from 'src/app/services/classe.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  alunos: any;
  classes: any;
  usuarios: any;
  constructor(
    private alunoService: AlunoService,
    private usuarioService: UserService,
    private classeService: ClasseService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.totalDashboard();
  }
  totalDashboard() {
    this.alunoService.totalAlunos().subscribe((resposta) => {
      this.alunos = resposta;
    });
    this.classeService.totalClasses().subscribe((resposta) => {
      this.classes = resposta;
    });
    this.usuarioService.totalUsuarios().subscribe((resposta) => {
      this.usuarios = resposta;
    });
  }

  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000
    });
  }

}
