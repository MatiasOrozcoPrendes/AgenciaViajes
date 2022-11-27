package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "personas")
@SQLDelete(sql = "UPDATE personas SET deleted = true WHERE id = ?", check = ResultCheckStyle.NONE) 
@Where(clause = "deleted = false")
public class Persona extends Base {

    @Column(name = "ci", nullable = false, length = 8, unique = true)
    private Long ci;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    public Long getCi() {
        return this.ci;
    }

    public void setCi(Long ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Persona() {
    }

    public Persona(Long ci, String nombre, String apellido, String email, String password) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }


    
}
