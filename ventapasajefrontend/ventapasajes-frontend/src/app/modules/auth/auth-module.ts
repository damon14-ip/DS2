import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { LoginComponent } from './login/login';
import { RegistroComponent } from './registro/registro';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LoginComponent,
    RegistroComponent
  ]
})
export class AuthModule {}