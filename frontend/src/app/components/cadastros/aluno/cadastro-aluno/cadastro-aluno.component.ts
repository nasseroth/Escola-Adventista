import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SharedService } from 'src/app/services/shared.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { AlunoService } from 'src/app/services/aluno.service';
import { ClasseService } from 'src/app/services/classe.service';

@Component({
  selector: 'app-cadastro-aluno',
  templateUrl: './cadastro-aluno.component.html',
  styleUrls: ['./cadastro-aluno.component.scss']
})
export class CadastroAlunoComponent implements OnInit {

  classes: any;
  formulario: FormGroup;
  shared: SharedService;
  constructor(
    private formBuilder: FormBuilder,
    private alunoService: AlunoService,
    private classeService: ClasseService,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.shared = SharedService.getInstance();
    //this.shared.user = new User('', '', '', '');

  }

  ngOnInit() {

    this.classeService.findAll().subscribe(data => {
      this.classes = data;
    });

    console.log('>>', this.shared.user);
    this.formulario = this.formBuilder.group({
      id: [null],
      nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(128)]],
      sexo: [null, Validators.required],
      codigoAluno: [null, Validators.required],
      classe: [null, Validators.required]
      //usuario: [{ id: this.shared.user.id }, Validators.required]
    });

    this.activatedRoute.params.subscribe((params: any) => {
      const id = params.id;
      if (id) {
        this.alunoService.findById(id).subscribe(aluno => {
          this.updateFormulario(aluno);
        });
      }
    });
  }

  onSubmit() {
    if (this.formulario.valid) {
      if (this.formulario.get('id').value) {
        this.alunoService.put(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Aluno atualizado com sucesso!');
        });
      } else {
        this.alunoService.post(this.formulario.value).subscribe(() => {
          this.abrirSnackBar('Aluno cadastrado com sucesso!');
        });
      }
    }
  }

  updateFormulario(aluno) {
    this.formulario.patchValue({
      id: aluno.id,
      nome: aluno.nome,
      codigoAluno: aluno.codigoAluno,
      numero: aluno.numero,
      sexo: aluno.sexo,
      classe: aluno.classe
    });
  }

  abrirSnackBar(msg: string) {
    this.snackBar.open(msg, '', {
      duration: 3000,
    });
  }
}
