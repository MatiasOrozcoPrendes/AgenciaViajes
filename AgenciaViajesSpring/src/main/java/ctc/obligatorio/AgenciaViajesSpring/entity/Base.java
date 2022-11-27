package ctc.obligatorio.AgenciaViajesSpring.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;



@MappedSuperclass
public class Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

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
