import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { AlunoService } from 'src/app/services/aluno.service';
import { MatSnackBar, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-lista-aluno',
  templateUrl: './lista-aluno.component.html',
  styleUrls: ['./lista-aluno.component.scss']
})
export class ListaAlunoComponent implements OnInit {


  alunos: any;
  displayedColumns: string[] = ['nome', 'codigoAluno', 'classe', 'dataCriacao', 'action'];
  shared: SharedService;
  usuario: any;
  constructor(
    private alunoService: AlunoService,
    private snackBar: MatSnackBar
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.buscarAlunos();
  }

  deletar(id: number) {
    return this.alunoService.delete(id).subscribe(data => {
      this.abrirSnackBar('Aluno deletado com sucesso!');
      this.buscarAlunos();
    }, err => {
      this.abrirSnackBar('Erro ao deletar aluno');
    });
  }
/*
  buscarAlunos() {
    return this.alunoService.findAll().subscribe(data => {
      this.alunos = new MatTableDataSource(data);
    });
  }*/
  buscarAlunos() {
    return this.alunoService.findAll().subscribe(data => {
      this.alunos = new MatTableDataSource(data);
    });
  }
  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }


}
