package ctc.obligatorio.AgenciaViajesSpring.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "viajes")
@SQLDelete(sql = "UPDATE viajes SET deleted = true WHERE id = ?", check = ResultCheckStyle.NONE) 
@Where(clause = "deleted = false")
public class Viaje extends Base {

    @Column(nullable = false, length = 20)
    private String destino;
    
    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es-ES", timezone = "America/Montevideo")
    private Date fechaInicio;

    @Column(nullable = false)
    private char modalidad;

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
