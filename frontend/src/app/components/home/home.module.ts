import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { MatCardModule, MatButtonModule, MatSnackBarModule } from '@angular/material';

@NgModule({
  declarations: [HomeComponent],
  imports: [
    CommonModule,
    HomeRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule
  ]
})
export class HomeModule { }
