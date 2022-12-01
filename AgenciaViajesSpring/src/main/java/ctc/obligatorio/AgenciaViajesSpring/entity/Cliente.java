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

//Esta clase se utiliza para indicar que esta clase es una entidad
@Entity
//Esta clase se utiliza para indicar que la referencia a la tabla es por el atributo id
@PrimaryKeyJoinColumn(referencedColumnName = "id")
//Esta clase se utiliza para indicar que esta clase se mapea a una tabla con el nombre "cliente"
@Table(name = "clientes")
public class Cliente extends Persona {

    //Como no es un usuario del tipo adeministrador, este atributo siempre es false
    private Boolean esAdministrador = false;
    //un cliente puede ser vip o no dependiendo de la cantidad de viajes que haya realizado si es mayor a 3 es vip
    private Boolean esVip;
    
    //Se utiliza la anotacion @ManyToMany para indicar que este atributo es una relacion muchos a muchos y se utiliza cascade para indicar que si se borra un cliente se borran los viajes que tiene asociados
    @ManyToMany(cascade = CascadeType.ALL)
    //Se utiliza la anotacion @JoinTable para indicar que la tabla intermedia se llama "cliente_viaje" y que la columna que referencia al cliente es "cliente_id" y la que referencia al viaje es "viaje_id"
    @JoinTable(
        name = "clientes_viajes", 
        joinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "id_viaje", referencedColumnName = "id"))
    //Lista de viajes que tiene asociados el cliente    
    private List<Viaje> viajes= new ArrayList<Viaje>();

    public Boolean getEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(Boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public Boolean getEsVip() {
        //Si la cantidad de viajes es mayor a 3 es vip
        if (this.viajes.size() >= 3) {
            this.esVip = true;
         } else {
            this.esVip = false;
         }
        return esVip;
    }

    public void setEsVip() {
        //Si la cantidad de viajes es mayor a 3 es vip
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
