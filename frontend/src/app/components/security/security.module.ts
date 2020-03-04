import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule, MatSnackBarModule, MatSelectModule, MatCardModule } from '@angular/material';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatFormFieldModule} from '@angular/material/form-field';


@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    ReactiveFormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatTableModule,
    HttpClientModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCardModule,
    MatFormFieldModule
  ]
})
export class SecurityModule { }
