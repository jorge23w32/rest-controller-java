package med.voll.api.domain.service.validaciones;

import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoActivo implements ValidadorConsultas{
    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta consulta){
        if(consulta.idMedico() == null){
            return;
        }
        var medicoEstaActivo = repository.findActivoById(consulta.idMedico());
        if(!medicoEstaActivo){
            throw new ValidacionException("La consulta no se puede reservar ya que el medico no esta activo");
        }
    }
}
