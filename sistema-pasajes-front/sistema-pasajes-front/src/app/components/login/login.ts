import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html'
})
export class LoginComponent {
  loginData = { email: '', password: '' };

  constructor(private auth: AuthService, private router: Router) {}

 onLoginSubmit() {
  this.auth.login(this.loginData).subscribe({
    next: () => {
      // Obtenemos el usuario que se acaba de loguear
      const user = this.auth.currentUser(); 
      
      alert('¡Bienvenido!');

      // REDIRECCIÓN SEGÚN ROL
      if (user && user.rol === 'ADMIN') {
        this.router.navigate(['/admin/dashboard']);
      } else {
        this.router.navigate(['/reservas']);
      }
    },
    error: () => alert('Error: Credenciales inválidas')
  });
}
}