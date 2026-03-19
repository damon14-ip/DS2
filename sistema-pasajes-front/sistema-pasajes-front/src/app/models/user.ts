export interface Usuario {
  idUsuario?: number; // El ? es porque al crear uno nuevo, aún no tiene ID
  nombre: string;
  apellido: string;
  dni: string;       // El nuevo campo que agregamos
  email: string;
  password?: string; // Opcional porque no siempre lo recibimos del backend
  rol: 'ADMIN' | 'VENDEDOR' | 'CLIENTE'; // Usamos tipos literales para evitar errores
  activo: boolean;
}

// Este coincide con tu UsuarioDTO.java del Backend
export interface UsuarioDTO {
  id: number;
  nombreCompleto: string;
  email: string;
  rol: string;
  dni: string;
}