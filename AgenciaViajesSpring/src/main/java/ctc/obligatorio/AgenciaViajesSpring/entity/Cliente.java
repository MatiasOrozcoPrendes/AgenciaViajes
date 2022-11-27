package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
@Table(name = "clientes")
public class Cliente extends Persona {

    private Boolean esAdministrador = false;
    //un cliente puede ser vip o no dependiendo de la cantidad de viajes que haya realizado si es mayor a 3 es vip
    private Boolean esVip;
    

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "clientes_viajes", 
        joinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_viaje", referencedColumnName = "id"))
    private List<Viaje> viajes= new ArrayList<Viaje>();

    public Boolean getEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(Boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public Boolean getEsVip() {
        if (this.viajes.size() >= 3) {
            this.esVip = true;
         } else {
            this.esVip = false;
         }
        return esVip;
    }

    public void setEsVip() {
        if (this.viajes.size() >= 3) {
            this.esVip = true;
        } else {
            this.esVip = false;
        }
    }

    public List<Viaje> getViajes() {
        return viajes;
    }

    public void setViajes(List<Viaje> viajes) {
        this.viajes = viajes;
    }

    public void addViaje(Viaje viaje) {
        this.viajes.add(viaje);
    }

    public void removeViaje(Viaje viaje) {
        this.viajes.remove(viaje);
    }

    public Cliente() {
    }

    public Cliente(Long ci, String nombre, String apellido, String email, String password) {
        super(ci, nombre, apellido, email, password);
    }


    
    
    
    
}
