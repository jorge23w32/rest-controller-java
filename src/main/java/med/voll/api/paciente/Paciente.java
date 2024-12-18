package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String usuario;
    @Embedded
    private Direccion direccion;
    private boolean activo;

    public Paciente(DatosRegistroPaciente paciente){
        this.nombre = paciente.nombre();
        this.email = paciente.email();
        this.usuario = paciente.usuario();
        this.direccion = new Direccion(paciente.direccion());
        this.activo = true;
    }

    public void actualizarPaciente(DatosActualizarPaciente actualizarPaciente){
        if(actualizarPaciente.nombre() != null){
            this.nombre = actualizarPaciente.nombre();
        }else if (actualizarPaciente.usuario() !=null){
            this.usuario = actualizarPaciente.usuario();
        } else if (actualizarPaciente.direccion() != null){
            this.direccion = direccion.actualizarDatos(actualizarPaciente.direccion());
        }
    }

    public void eliminarPaciente(){
        this.activo = false;
    }
}
