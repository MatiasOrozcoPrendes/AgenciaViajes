package ctc.obligatorio.AgenciaViajesSpring.service.iplement;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;
import ctc.obligatorio.AgenciaViajesSpring.repository.RepositoryViaje;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceViaje;

//Se utiliza la anotacion @Service para indicar que esta clase es un servicio
@Service("serviceViaje")
//Esta clase implementa la interfaz ServiceViaje
public class ServiceViajeImplement implements ServiceViaje {

    //Se utiliza la anotacion @Autowired para que se inyecte la dependencia de la interfaz RepositoryViaje
    @Autowired
    private RepositoryViaje repositoryViaje;
    
    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Iterable de Viaje con todos los viajes que se encuentran en la base de datos
    public Iterable<Viaje> findAll() {
        return repositoryViaje.findAll();
    }

    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Viaje con el viaje que tenga el id que se le pasa por parametro si existe
    public Optional<Viaje> findById(Long id) {
        return repositoryViaje.findById(id);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que guarda un viaje en la base de datos
    public Viaje save(Viaje viaje) {
        return repositoryViaje.save(viaje);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que elimina un viaje de la base de datos
    public void deleteById(Long id) {
        repositoryViaje.deleteById(id);
    }

    @Override
    //Se devuelve un Page de Viaje con todos los viajes que se encuentran en la base de datos
    public Page<Viaje> findAll(Pageable pageable) {
        return repositoryViaje.findAll(pageable);
    }

    @Override
    //Se devuelve un Iterable de Viaje con todos los viajes que se encuentran en la base de datos y que tengan fechaInicio mayor a la fecha que se le pasa por parametro
    public Iterable<Viaje> findByFechaInicioGreaterThan(String fecha) {
        return repositoryViaje.findByFechaInicioGreaterThan(Date.valueOf(fecha));
    }
}
