package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorario implements ValidadorConsultas{

    public void validar(DatosReservaConsulta consulta){
        var fechaConsulta = consulta.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioApertura = fechaConsulta.getHour()< 7;
        var horarioCierre = fechaConsulta.getHour() > 18;

        if(domingo || horarioApertura || horarioCierre){
            throw new ValidacionException("Horario seleccionado fuera del horario de atendimiento de la clinica");
        }
    }
}
