package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagenes")
public class Imagen extends Base {

    @Column(nullable = false)
    private String imagen;
    
    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;


    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Viaje getViaje() {
        return this.viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

}
