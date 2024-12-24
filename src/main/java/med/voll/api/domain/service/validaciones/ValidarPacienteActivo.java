package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteActivo implements ValidadorConsultas{
    @Autowired
    private PacienteRepository repository;
    public void validar(DatosReservaConsulta consulta){
        var pacienteEstaActivo = repository.findActivoById(consulta.idPaciente());
        if(!pacienteEstaActivo){
            throw new ValidacionException("La consulta no puede ser reservada ya que el paciente no esta activo");
        }
    }
}
