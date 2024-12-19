package med.voll.api.domain.paciente;

public record DatosListadoPacientes(
        Long id,
        String nombre,
        String email,
        String usuario
) {
    public DatosListadoPacientes(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getUsuario());
    }
}
