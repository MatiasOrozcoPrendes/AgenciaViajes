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

@Service("serviceCliente")
public class ServiceClienteImplement implements ServiceCliente {
    
    @Autowired
    private RepositoryCliente repositoryCliente;

    @Autowired
    private RepositoryViaje repositoryViaje;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Cliente> findAll() {
        return repositoryCliente.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return repositoryCliente.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findByEmail(String email) {
        return repositoryCliente.findByEmail(email);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return repositoryCliente.save(cliente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositoryCliente.deleteById(id);
    }

    @Override
    public Page<Cliente> findAll(Pageable pageable) {
        return repositoryCliente.findAll(pageable);
    }

    @Override
    public Cliente addViaje(Long idCliente, Long idViaje) {
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
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
    public Cliente removeViaje(Long idCliente, Long idViaje) {
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
        Optional<Viaje> viaje = repositoryViaje.findById(idViaje);
        if (cliente.isPresent() && viaje.isPresent()) {
            cliente.get().removeViaje(viaje.get());
            return repositoryCliente.save(cliente.get());
        }
        return cliente.get();
    }

    @Override
    public Viaje primerViajeDespuesDeFecha(Long idCliente, String fecha) {
        // creo una variable de fecha para comparar con la fecha del viaje
        Date fechaDate = Date.valueOf(fecha);
        Optional<Cliente> cliente = repositoryCliente.findById(idCliente);
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
    public Optional<Cliente> findByEmailAndPassword(String email, String password) {
        return repositoryCliente.findByEmailAndPassword(email, password);
    }

}
