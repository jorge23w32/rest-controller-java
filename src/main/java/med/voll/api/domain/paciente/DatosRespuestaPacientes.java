package med.voll.api.domain.paciente;

import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.direccion.Direccion;

public record DatosRespuestaPacientes(Long id, String nombre, String usuario, DatosDireccion direccion) {
}
