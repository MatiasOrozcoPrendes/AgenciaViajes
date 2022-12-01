package ctc.obligatorio.AgenciaViajesSpring.service.iplement;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctc.obligatorio.AgenciaViajesSpring.entity.Cliente;
import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;
import ctc.obligatorio.AgenciaViajesSpring.repository.RepositoryCliente;
import ctc.obligatorio.AgenciaViajesSpring.repository.RepositoryViaje;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceCliente;

//Se utiliza la anotacion @Service para indicar que esta clase es un servicio
@Service("serviceCliente")
//Esta clase implementa la interfaz ServiceCliente
public class ServiceClienteImplement implements ServiceCliente {
    
    //Se utiliza la anotacion @Autowired para que se inyecte la dependencia de la interfaz RepositoryCliente
    @Autowired
    private RepositoryCliente repositoryCliente;

    //Se utiliza la anotacion @Autowired para que se inyecte la dependencia de la interfaz RepositoryViaje
    @Autowired
    private RepositoryViaje repositoryViaje;
    
    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Iterable de Cliente 
    public Iterable<Cliente> findAll() {
        return repositoryCliente.findAll();
    }

    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Cliente con el cliente que tenga el id que se le pasa por parametro si existe
    public Optional<Cliente> findById(Long id) {
        return repositoryCliente.findById(id);
    }

    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Cliente con el cliente que tenga el email que se le pasa por parametro si existe
    public Optional<Cliente> findByEmail(String email) {
        return repositoryCliente.findByEmail(email);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que guarda un cliente en la base de datos
    public Cliente save(Cliente cliente) {
        return repositoryCliente.save(cliente);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que elimina un cliente de la base de datos
    public void deleteById(Long id) {
        repositoryCliente.deleteById(id);
    }

    @Override
    //Se devuelve un Page de Cliente con los clientes que se encuentran en la base de datos
    public Page<Cliente> findAll(Pageable pageable) {
        return repositoryCliente.findAll(pageable);
    }

    @Override
    //Se agregan los viajes que se le pasan por parametro al cliente que se le pasa por parametro
    public Cliente addViaje(Long idCliente, Long idViaje) {
        //Se obtiene el cliente que se le pasa por parametro
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
        //Se obtiene el viaje que se le pasa por parametro
        Optional<Viaje> viaje = repositoryViaje.findById(idViaje);
        // si hay cliente y viaje agrego el viaje al cliente
        if (cliente.isPresent() && viaje.isPresent()) {
            // agrego el viaje al cliente
            cliente.get().addViaje(viaje.get());
            //actualizo el valor de esVip
            cliente.get().setEsVip();
            //actualizo el cliente en la base de datos y retorno el cliente
            return repositoryCliente.save(cliente.get());
        }
        // si no hay cliente o viaje retorno el cliente o un cliente vacio
        return cliente.get();
    }

    @Override
    //Se elimina el viaje que se le pasa por parametro al cliente que se le pasa por parametro
    public Cliente removeViaje(Long idCliente, Long idViaje) {
        //Se obtiene el cliente que se le pasa por parametro
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
        //Se obtiene el viaje que se le pasa por parametro
        Optional<Viaje> viaje = repositoryViaje.findById(idViaje);
        // si hay cliente y viaje elimino el viaje al cliente
        if (cliente.isPresent() && viaje.isPresent()) {
            // elimino el viaje al cliente
            cliente.get().removeViaje(viaje.get());
            //actualizo el cliente con el viaje eliminado en la base de datos y retorno el cliente
            return repositoryCliente.save(cliente.get());
        }
        return cliente.get();
    }

    @Override
    //Se obtiene el primer viaje del cliente que se le pasa por parametro con la fecha posterior a la fecha que se le pasa por parametro
    public Viaje primerViajeDespuesDeFecha(Long idCliente, String fecha) {
        // creo una variable de fecha para comparar con la fecha del viaje
        Date fechaDate = Date.valueOf(fecha);
        //Se obtiene el cliente que se le pasa por parametro
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
        // si hay cliente
        if (cliente.isPresent()) {
            // creo un array de viajes con los viajes del cliente y lo ordeno por fecha 
            Viaje[] viajes = cliente.get().getViajes().stream().sorted((v1, v2) -> v1.getFechaInicio().compareTo(v2.getFechaInicio())).toArray(Viaje[]::new);
            // recorro el array de viajes y retorno el primer viaje que tenga fecha mayor a la fecha pasada por parametro
            for (Viaje viaje : viajes) {
                // si la fecha del viaje es mayor o igual a la fecha pasada por parametro retorno el viaje
                if (viaje.getFechaInicio().compareTo(fechaDate) >= 0) {
                    return viaje;
                }
            }
        }
        return null;
    }

    @Override
    // Devuelve un Optional de Cliente con el cliente que tenga el email y la contraseña que se le pasan por parametro
    public Optional<Cliente> findByEmailAndPassword(String email, String password) {
        return repositoryCliente.findByEmailAndPassword(email, password);
    }

}
