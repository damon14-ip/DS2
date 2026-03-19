import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../services/user';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './usuario-list.html'
})
export class UsuarioListComponent implements OnInit {
  usuarios: any[] = [];

  constructor(
    private userService: UserService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.userService.listar().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.cdr.detectChanges(); // Vital para que la tabla se pinte
      },
      error: (err) => console.error(err)
    });
  }

  borrarUsuario(id: number) {
    if(confirm('¿Seguro que deseas eliminar este usuario?')) {
      this.userService.eliminar(id).subscribe({
        next: () => {
          this.cargarUsuarios();
          this.cdr.detectChanges();
        },
        error: (err) => alert('No se pudo eliminar el usuario')
      });
    }
  }
}