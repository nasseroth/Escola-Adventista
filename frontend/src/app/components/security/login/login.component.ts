import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from './../../../services/user.service';
import { SharedService } from './../../../services/shared.service';
import { User } from '../../../dto/user-dto';
import { CurrentUser } from '../../../dto/current-user-dto';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user = new User('', '', '', '');
  shared: SharedService;
  message: string;
  formulario: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
  ) {
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.formulario = this.formBuilder.group({
      email: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(128)]],
      senha: [null, Validators.required]
    });

  }

  login() {
    this.message = '';
    this.user.email = this.formulario.get('email').value;
    this.user.password = this.formulario.get('senha').value;
    this.userService.login(this.user).subscribe((userAuthentication: CurrentUser) => {
      this.shared.user = userAuthentication.user;
      this.shared.token = userAuthentication.token;
      this.shared.user.profile = userAuthentication.user.profile.substring(5);
      this.shared.showTemplate.emit(true);
      this.router.navigate(['/home']); // quando o usuario loga é redirecionado ao '' que seria o dashboard
    }, err => {
      this.shared.user = null;
      this.shared.token = null;
      this.shared.showTemplate.emit(false);
      this.message = 'Error';
    });
  }

  cancelLogin() {
    this.user = new User('', '', '', '');
    this.message = '';
    window.location.href = '/login';
    window.location.reload();
  }


}
