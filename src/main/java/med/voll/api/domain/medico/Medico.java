package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.direccion.Direccion;

@Entity(name = "Medico")
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    private Boolean activo;

    public Medico(DatosRegistroMedico datosMedico) {
        this.nombre = datosMedico.nombre();
        this.email = datosMedico.email();
        this.documento = datosMedico.documento();
        this.especialidad = datosMedico.especialidad();
        this.direccion = new Direccion(datosMedico.direccion());
        this.telefono = datosMedico.telefono();
        this.activo = true;
    }
    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico){
        if(datosActualizarMedico.nombre() != null){
            this.nombre = datosActualizarMedico.nombre();
        } else if (datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        } else if (datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }

    }

    public void desactivarMedico() {
        this.activo=false;
    }
}
