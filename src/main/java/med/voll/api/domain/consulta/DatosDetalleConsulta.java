package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idMedico,
        Especialidad especialidad,
        Long idPaciente,
        LocalDateTime fecha
) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getMedico().getEspecialidad(),
             consulta.getPaciente().getId(), consulta.getFecha());
    }
}
