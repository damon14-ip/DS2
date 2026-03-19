import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ReservaService } from '../../services/reserva';
import { VueloService } from '../../services/vuelo';

@Component({
  selector: 'app-reserva-form',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './reserva-form.html'
})
export class ReservaFormComponent implements OnInit {
  reservaForm: FormGroup;
  vuelos: any[] = [];

  constructor(
    private fb: FormBuilder,
    private reservaService: ReservaService,
    private vueloService: VueloService
  ) {
    // Inicializamos el formulario
    this.reservaForm = this.fb.group({
      idVuelo: [null, Validators.required],
      pasajeros: this.fb.array([]) // Array dinámico
    });
  }

  ngOnInit() {
    // Cargar vuelos para el select del formulario
    this.vueloService.listarTodos().subscribe(data => this.vuelos = data);
    
    // Agregamos el primer pasajero por defecto
    this.agregarPasajero();
  }

  // --- GETTER CRÍTICO PARA EL HTML ---
  // Esto soluciona el error: Property 'pasajeros' does not exist
  get pasajeros() {
    return this.reservaForm.get('pasajeros') as FormArray;
  }

  // --- MÉTODOS QUE PIDE TU HTML ---

  // Soluciona: Property 'agregarPasajero' does not exist
  agregarPasajero() {
    const pasajeroGroup = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      documento: ['', Validators.required],
      edad: [18, [Validators.required, Validators.min(0)]],
      tipoAsiento: ['TURISTA', Validators.required],
      asientoAleatorio: [true]
    });
    this.pasajeros.push(pasajeroGroup);
  }

  // Soluciona: Property 'removerPasajero' does not exist
  removerPasajero(index: number) {
    if (this.pasajeros.length > 1) {
      this.pasajeros.removeAt(index);
    }
  }

  // Soluciona: Property 'confirmar' does not exist
  confirmar() {
    if (this.reservaForm.valid) {
      this.reservaService.enviarReserva(this.reservaForm.value).subscribe({
        next: (res: Blob) => {
          const url = window.URL.createObjectURL(res);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'ticket_reserva.pdf';
          a.click();
          window.URL.revokeObjectURL(url);
          alert('✅ Reserva confirmada y ticket descargado.');
        },
        error: async (err) => {
          const msg = err.error instanceof Blob ? await err.error.text() : 'Error en el servidor';
          alert('❌ ' + msg);
        }
      });
    } else {
      alert('⚠️ Por favor revisa los datos del formulario.');
    }
  }
}