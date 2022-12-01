package ctc.obligatorio.AgenciaViajesSpring.repository;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;

//Se utiliza la anotacion @Repository para indicar que esta clase es un repositorio
@Repository("repositoryViaje")
//Esta interfaz extiende de JpaRepository para que se pueda utilizar los metodos de esta interfaz
public interface RepositoryViaje extends JpaRepository<Viaje, Serializable> {

    //listar todos los viajes con fechaInicio mayor a la fecha actual
    public abstract List<Viaje> findByFechaInicioGreaterThan(Date fechaActual);

    
}
