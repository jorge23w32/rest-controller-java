package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacionConsultaAnticipada implements ValidadorConsultas{

    public void validar(DatosReservaConsulta consulta){
        var fechaConsulta = consulta.fecha();
        var fechaActual = LocalDateTime.now();
        var diferenciaMinutos = Duration.between(fechaActual, fechaConsulta).toMinutes();
        if(diferenciaMinutos < 30){
            throw new ValidacionException("Horario menor a 30 minutos de anticipacion");
        }
    }
}
