package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public ResponseEntity<DatosRespuestaPacientes> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosPaciente, UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = repository.save(new Paciente(datosPaciente));
        var datosDireccion= new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getCuidad(), paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento());
        var datosRespuestaPacientes = new DatosRespuestaPacientes(paciente.getId(),  paciente.getNombre(), paciente.getUsuario(), datosDireccion);
        URI url = uriComponentsBuilder.path("/medicos/{id").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPacientes);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPacientes>> mostrarPacientes(@PageableDefault(size = 5)Pageable pageable){
        return ResponseEntity.ok(repository.findByActivoTrue(pageable).map(DatosListadoPacientes::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPacientes> actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente actualizarPaciente){
        Paciente paciente = repository.getReferenceById(actualizarPaciente.id());
        paciente.actualizarPaciente(actualizarPaciente);
        var datosDireccion= new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getCuidad(), paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento());
        var datosRespuestaPacientes = new DatosRespuestaPacientes(paciente.getId(),  paciente.getNombre(), paciente.getUsuario(), datosDireccion);
        return ResponseEntity.ok(datosRespuestaPacientes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaPacientes> eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.eliminarPaciente();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPacientes> devolverDatosPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        var datosDireccion= new DatosDireccion(paciente.getDireccion().getCalle(), paciente.getDireccion().getDistrito(), paciente.getDireccion().getCuidad(), paciente.getDireccion().getNumero(),paciente.getDireccion().getComplemento());
        var datosRespuestaPacientes = new DatosRespuestaPacientes(paciente.getId(),  paciente.getNombre(), paciente.getUsuario(), datosDireccion);
        return ResponseEntity.ok(datosRespuestaPacientes);
    }


}
