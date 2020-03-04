import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { ClasseService } from 'src/app/services/classe.service';
import { MatSnackBar, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-lista-classe',
  templateUrl: './lista-classe.component.html',
  styleUrls: ['./lista-classe.component.scss']
})
export class ListaClasseComponent implements OnInit {

  classes: any;
  displayedColumns: string[] = ['id', 'nome', 'numero', 'dataCriacao', 'action'];
  shared: SharedService;
  usuario: any;
  constructor(
    private classeService: ClasseService,
    private snackBar: MatSnackBar
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.buscarClasses();
  }

  deletar(id: number) {
    return this.classeService.delete(id).subscribe(data => {
      this.abrirSnackBar('Classe deletado com sucesso!');
      this.buscarClasses();
    }, err => {
      this.abrirSnackBar('Erro ao deletar classe');
    });
  }
/*
  buscarClasses() {
    return this.classeService.findAll().subscribe(data => {
      this.classes = new MatTableDataSource(data);
    });
  }*/
  buscarClasses() {
    console.log('passou aqui');
    this.usuario = this.shared.user.id;
    return this.classeService.findAll().subscribe(data => {
      this.classes = new MatTableDataSource(data);
    });
  }
  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }

}
