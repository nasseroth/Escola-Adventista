import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SharedService } from 'src/app/services/shared.service';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { User } from 'src/app/dto/user-dto';
import { ResponseApi } from 'src/app/dto/response-api';

@Component({
  selector: 'app-cadastro-usuario',
  templateUrl: './cadastro-usuario.component.html',
  styleUrls: ['./cadastro-usuario.component.scss']
})
export class CadastroUsuarioComponent implements OnInit {

  user: User = new User('', '', '', '');
  formulario: FormGroup;
  shared: SharedService;
  constructor(
    private formBuilder: FormBuilder,
    private usuarioService: UserService,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.shared = SharedService.getInstance();
    //this.shared.user = new User('', '', '', '');

  }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      id: [null],
      email: [null, Validators.required],
      password: [null, Validators.required],
      profile: [null, Validators.required]
    });
    this.activatedRoute.params.subscribe((params: any) => {
      const id = params.id;
      if (id) {
        this.usuarioService.findById(id).subscribe(usuario => {
          this.updateFormulario(usuario);
        });
      }
    });
  }

  onSubmit() {
    if (this.formulario.valid) {
      if (this.formulario.get('id').value) {
        this.usuarioService.post(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Usuário atualizado com sucesso!');
        });
      } else {
        this.usuarioService.post(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Usuário cadastrado com sucesso!');
        });
      }
    }
  }
/*
  register() {
    this.usuarioService.createOrUpdate(this.user).subscribe((responseApi: ResponseApi) => {
      console.log('chamou registro');
      this.user = new User('', '', '', '');
      let userRet: User = responseApi.data;
      this.formulario.reset();
    });
  }*/
  updateFormulario(usuario) {
    this.formulario.patchValue({
      id: usuario.id,
      email: usuario.email,
      //password: usuario.password,
      profile: usuario.profile
    });
  }

  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }

}
