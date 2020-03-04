

import { CadastrosModule } from './components/cadastros/cadastros.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { SharedService } from './services/shared.service';
import { AuthGuard } from './components/security/auth.guard';
import { DialogService } from './services/dialog.service';
import { AuthInterceptor } from './components/security/auth.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule, MatButtonModule, MatSidenavModule, MatIconModule, MatListModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavComponent } from './components/layout/nav/nav.component';
import { HomeModule } from './components/home/home.module';
import { LoginComponent } from './components/security/login/login.component';
import { SecurityModule } from './components/security/security.module';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserAnimationsModule,
    HomeModule,
    CadastrosModule,
    FormsModule,
    SecurityModule
  ],
  providers: [
    SharedService,
    AuthGuard,
    DialogService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
