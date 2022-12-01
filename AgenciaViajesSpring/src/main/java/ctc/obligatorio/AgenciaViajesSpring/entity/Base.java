package ctc.obligatorio.AgenciaViajesSpring.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//Esta clase se utiliza para que las entidades que hereden de esta no tengan que repetir el id y el booleano deleted
//Esta clase no se mapea a una tabla
@MappedSuperclass
//Esta clase implementa la interfaz Serializable para que los objetos de esta clase puedan ser serializados
public class Base implements Serializable {

    //Se utiliza la anotacion @Id para indicar que este atributo es el id de la entidad
    @Id
    //Se utiliza la anotacion @GeneratedValue para indicar que el id se genera automaticamente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //Este atributo se utiliza para indicar si el registro esta borrado o no de la base de datos (soft delete)
    private boolean deleted;

    public Long getId() {
        return Id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Base() {
    }

    public Base(Long Id, boolean deleted) {
        this.Id = Id;
        this.deleted = deleted;
    }


}
