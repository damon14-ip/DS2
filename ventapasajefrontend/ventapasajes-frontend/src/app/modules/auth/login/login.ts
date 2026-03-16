import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class LoginComponent {

  email = '';
  password = '';
  mensaje = '';

  apiUrl = 'http://localhost:8080/api/auth/login';

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login() {

    const body = {
      email: this.email,
      password: this.password
    };

    this.http.post<any>(this.apiUrl, body).subscribe({

      next: (res) => {

        // Guardar token
        localStorage.setItem('token', res.token);

        // Guardar usuario
        localStorage.setItem('usuario', res.email);

        // Guardar rol
        localStorage.setItem('rol', res.rol);

        // Redirigir
        this.router.navigate(['/vuelos']);

      },

      error: () => {
        this.mensaje = "Usuario o contraseña incorrectos";
      }

    });

  }

}