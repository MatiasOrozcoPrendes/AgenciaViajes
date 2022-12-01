package ctc.obligatorio.AgenciaViajesSpring.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

//Se utiliza la anotacion @Entity para indicar que esta clase es una entidad
@Entity
//Se utiliza la anotacion @Table para indicar el nombre de la tabla
@Table(name = "viajes")
//Se utiliza la anotacion @SQLDelete para que al eliminar un registro se cambie el valor del atributo deleted a true
@SQLDelete(sql = "UPDATE viajes SET deleted = true WHERE id = ?", check = ResultCheckStyle.NONE) 
//Se utiliza la anotacion @Where para que solo se traigan los registros que tengan el atributo deleted en false
@Where(clause = "deleted = false")
public class Viaje extends Base {

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo y que su longitud maxima es de 20 caracteres
    @Column(nullable = false, length = 20)
    private String destino;
    
    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo
    @Column(nullable = false)
    //Se utiliza la anotacion @JsonFormat para indicar el formato de fecha que se espera recibir
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Montevideo")
    private Date fechaInicio;

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo
    @Column(nullable = false)
    private char modalidad;

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo
    @Column(nullable = false)
    private Double precio;

    public String getDestino() {
        return this.destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public char getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(char modalidad) {
        this.modalidad = modalidad;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }


    public Viaje() {
    }

    public Viaje(String destino, Date fechaInicio, char modalidad, Double precio) {
        this.destino = destino;
        this.fechaInicio = fechaInicio;
        this.modalidad = modalidad;
        this.precio = precio;
    }


    

        
}
