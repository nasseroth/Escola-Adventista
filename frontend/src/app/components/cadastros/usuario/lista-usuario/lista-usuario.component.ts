import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar, MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.scss']
})
export class ListaUsuarioComponent implements OnInit {

  usuarios: any;
  displayedColumns: string[] = ['id', 'email', 'profile', 'action'];

  constructor(
    private usuarioService: UserService,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit() {
    this.buscarUsuarios();
  }

  deletar(id: number) {
    return this.usuarioService.delete(id).subscribe(data => {
      this.abrirSnackBar('Usuário deletado com sucesso!');
      this.buscarUsuarios();
    }, err => {
      this.abrirSnackBar('Erro ao deletar usuário');
    });
  }

  buscarUsuarios() {
    return this.usuarioService.findAll().subscribe(data => {
      this.usuarios = new MatTableDataSource(data);
    });
  }

  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }


}
