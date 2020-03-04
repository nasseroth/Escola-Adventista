import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { SharedService } from 'src/app/services/shared.service';
import { ClasseService } from 'src/app/services/classe.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-cadastro-classe',
  templateUrl: './cadastro-classe.component.html',
  styleUrls: ['./cadastro-classe.component.scss']
})
export class CadastroClasseComponent implements OnInit {

  formulario: FormGroup;
  shared: SharedService;
  constructor(
    private formBuilder: FormBuilder,
    private classeService: ClasseService,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.shared = SharedService.getInstance();
    //this.shared.user = new User('', '', '', '');

  }

  ngOnInit() {
    console.log('>>', this.shared.user);
    this.formulario = this.formBuilder.group({
      id: [null],
      nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(128)]],
      numero: [null, Validators.required]
      //usuario: [{ id: this.shared.user.id }, Validators.required]
    });

    this.activatedRoute.params.subscribe((params: any) => {
      const id = params.id;
      if (id) {
        this.classeService.findById(id).subscribe(classe => {
          this.updateFormulario(classe);
        });
      }
    });
  }

  onSubmit() {
    if (this.formulario.valid) {
      if (this.formulario.get('id').value) {
        this.classeService.put(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Classe atualizado com sucesso!');
        });
      } else {
        this.classeService.post(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Classe cadastrado com sucesso!');
        });
      }
    }
  }

  updateFormulario(classe) {
    this.formulario.patchValue({
      id: classe.id,
      nome: classe.nome,
      numero: classe.numero
    });
  }

  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }
}
