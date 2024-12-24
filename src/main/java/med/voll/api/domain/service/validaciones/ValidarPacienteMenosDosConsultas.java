package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarPacienteMenosDosConsultas implements ValidadorConsultas{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta consulta){
        var abierto = consulta.fecha().withHour(7);
        var cerrado = consulta.fecha().withHour(18);
        var pacienteTieneVariasConsultas = repository.existsByPacienteIdAndFechaBetween(consulta.idPaciente(), abierto, cerrado);
        if(pacienteTieneVariasConsultas){
            throw new ValidacionException("El paciente solo puede tener una consulta al dia");
        }
    }
}
