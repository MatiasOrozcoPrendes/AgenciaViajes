package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "usuarios")
public class Usuario extends Persona {

    private Boolean esAdministrador = true;

    public Boolean getEsAdministrador() {
        return esAdministrador;
    }

    public Usuario() {
    }

    public Usuario(Long ci, String nombre, String apellido, String email, String password) {
        super(ci, nombre, apellido, email, password);
    }
    

    
    
}
