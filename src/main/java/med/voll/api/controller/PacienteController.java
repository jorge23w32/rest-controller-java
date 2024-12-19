package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente paciente){
        repository.save(new Paciente(paciente));
    }

    @GetMapping
    public Page<DatosListadoPacientes> mostrarPacientes(@PageableDefault(size = 5)Pageable pageable){
        return repository.findByActivoTrue(pageable).map(DatosListadoPacientes::new);
    }

    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente actualizarPaciente){
        Paciente paciente = repository.getReferenceById(actualizarPaciente.id());
        paciente.actualizarPaciente(actualizarPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.eliminarPaciente();
    }
}
