package ctc.obligatorio.AgenciaViajesSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

//Se utiliza la anotacion @Entity para indicar que esta clase es una entidad
@Entity
//Se utiliza la anotacion @Inheritance para indicar que esta clase es una clase padre y que las clases que hereden de esta se mapearan a una tabla por cada clase hija 
@Inheritance(strategy = InheritanceType.JOINED)
//Se utiliza la anotacion @Table para indicar el nombre de la tabla a la que se mapea esta clase
@Table(name = "personas")
//Se utiliza la anotacion @SQLDelete para indicar que al eliminar un registro se debe actualizar el atributo deleted a true en vez de eliminar el registro de la base de datos
@SQLDelete(sql = "UPDATE personas SET deleted = true WHERE id = ?", check = ResultCheckStyle.NONE) 
//Se utiliza la anotacion @Where para que solo se traigan los registros que tengan el atributo deleted en false
@Where(clause = "deleted = false")
//Esta clase se utiliza para que las entidades que hereden de esta no tengan que repetir los atributos comunes a clientes y usuarios
public class Persona extends Base {

    //Se utiliza la anotacion @Column para indicar el nombre de la columna a la que se mapea este atributo, que no puede ser nulo, que debe ser unico y que su longitud maxima es de 8 caracteres
    @Column(name = "ci", nullable = false, length = 8, unique = true)
    private Long ci;

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo y que su longitud maxima es de 30 caracteres
    @Column(nullable = false, length = 30)
    private String nombre;

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo y que su longitud maxima es de 30 caracteres
    @Column(nullable = false, length = 30)
    private String apellido;

    //Se utiliza la anotacion @Column para indicar que el valor no puede ser nulo, que su longitud maxima es de 30 caracteres y que debe ser unico
    @Column(nullable = false, length = 30, unique = true)
    private String email;

    //Se utiliza la anotacion @Column para indicar el nombre de la columna a la que se mapea este atributo, que no puede ser nulo y que su longitud maxima es de 30 caracteres
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
