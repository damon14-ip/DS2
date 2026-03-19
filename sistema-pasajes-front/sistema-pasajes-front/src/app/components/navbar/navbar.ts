import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // <--- IMPORTANTE
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule], // <--- AÑADIR AQUÍ
  templateUrl: './navbar.html'
})
export class NavbarComponent {
  constructor(public auth: AuthService) {} 
}