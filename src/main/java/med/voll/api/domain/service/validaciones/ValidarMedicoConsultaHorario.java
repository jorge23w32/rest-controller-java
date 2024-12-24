package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoConsultaHorario implements ValidadorConsultas{
    @Autowired
    private ConsultaRepository repository;
    public void validar(DatosReservaConsulta consulta){
        var medicoTieneConsultaMismaHora = repository.existsByMedicoIdAndFecha(consulta.idMedico(), consulta.fecha());
        if(medicoTieneConsultaMismaHora){
            throw new ValidacionException("El medico ya tiene resgitrada una consulta en ese horario");
        }
    }
}
