package med.voll.api.domain.service.consulta;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.service.validaciones.ValidadorConsultas;
import med.voll.api.infra.exception.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorConsultas> validadores;

    public DatosDetalleConsulta reservar(DatosReservaConsulta reservaConsulta){
        //Validaciones
        validadores.forEach(v -> v.validar(reservaConsulta));
        

        var medico = (reservaConsulta.idMedico() != null) ? medicoRepository.findById(reservaConsulta.idMedico()).orElseThrow() : (reservaConsulta.especialidad() != null) ? medicoRepository.findRandomMedico(reservaConsulta.especialidad(), reservaConsulta.fecha()).orElseThrow(()->new ValidacionException("La especialidad mencionada no existe")) : null;
        var paciente = pacienteRepository.findById(reservaConsulta.idPaciente()).orElseThrow(() -> new ValidacionException("No existe un paciente con el id informado"));
        var consulta = new Consulta(null, medico, paciente, reservaConsulta.fecha());
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }
}




















