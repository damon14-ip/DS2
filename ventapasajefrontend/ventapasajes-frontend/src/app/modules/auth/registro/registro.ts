import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registro.html',
  styleUrls: ['./registro.css']
})
export class RegistroComponent {

  username='';
  password='';
  email='';
  mensaje='';

  apiUrl='http://localhost:8080/api/auth/register';

  constructor(
    private http:HttpClient,
    private router:Router
  ){}

  registrar(){

    const body={
      username:this.username,
      password:this.password,
      email:this.email,
      rol:'CLIENTE'
    };

    this.http.post(this.apiUrl,body).subscribe({

      next:()=>{
        alert("Usuario registrado correctamente");
        this.router.navigate(['/login']);
      },

      error:()=>{
        this.mensaje="Error al registrar usuario";
      }

    });

  }

}