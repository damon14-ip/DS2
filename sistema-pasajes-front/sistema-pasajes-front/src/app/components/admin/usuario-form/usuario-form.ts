import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuario-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink], // RouterLink es vital aquí
  templateUrl: './usuario-form.html'
})
export class UsuarioFormComponent implements OnInit {
  
  userForm!: FormGroup;
  errorMessage: string | null = null; // Para mostrar errores del servidor

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    // Dentro de initForm() o ngOnInit()
this.userForm = this.fb.group({
  nombre: ['', [Validators.required, Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$/)]],
  apellido: ['', [Validators.required, Validators.pattern(/^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$/)]],
  // Validación para exactamente 8 números
  dni: ['', [
    Validators.required, 
    Validators.minLength(8), 
    Validators.maxLength(8), 
    Validators.pattern(/^[0-9]{8}$/)
  ]],
  email: ['', [Validators.required, Validators.email]],
  password: ['', [Validators.required, Validators.minLength(6)]],
  rol: ['VENDEDOR', Validators.required]
});
    this.cdr.detectChanges();
  }

  guardar() {
    if (this.userForm.invalid) {
      this.userForm.markAllAsTouched();
      return;
    }

    this.errorMessage = null; // Limpiar errores previos

    this.userService.crear(this.userForm.value).subscribe({
      next: () => {
        alert('Usuario creado con éxito');
        this.cdr.detectChanges(); 
        this.router.navigate(['/admin/usuarios']);
      },
      error: (err) => {
        // Validación de error del Backend (DNI o Email duplicado)
        if (err.status === 409 || err.status === 400) {
          this.errorMessage = "El DNI o el Email ya se encuentran registrados en el sistema.";
        } else {
          this.errorMessage = "Ocurrió un error inesperado al guardar el usuario.";
        }
        console.error(err);
        this.cdr.detectChanges();
      }
    });
  }

  isInvalid(field: string) {
    const control = this.userForm.get(field);
    return control ? control.invalid && (control.dirty || control.touched) : false;
  }
}