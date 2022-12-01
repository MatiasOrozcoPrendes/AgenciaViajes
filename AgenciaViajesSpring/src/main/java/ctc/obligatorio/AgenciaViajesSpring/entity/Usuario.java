package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

//Esta anotaion se utiliza para indicar que esta clase es una entidad
@Entity
//Esta anotacion se utiliza para indicar que la referencia a la tabla es por el atributo id
@PrimaryKeyJoinColumn(referencedColumnName = "id")
//Esta anotacion se utiliza para indicar el nombre de la tabla
@Table(name = "usuarios")
public class Usuario extends Persona {

    //Como es un usuario del tipo administrador, este atributo siempre es true
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
