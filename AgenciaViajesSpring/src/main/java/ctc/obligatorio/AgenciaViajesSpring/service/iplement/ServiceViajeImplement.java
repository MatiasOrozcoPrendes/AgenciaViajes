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

@Service("serviceViaje")
public class ServiceViajeImplement implements ServiceViaje {

    @Autowired
    private RepositoryViaje repositoryViaje;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Viaje> findAll() {
        return repositoryViaje.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Viaje> findById(Long id) {
        return repositoryViaje.findById(id);
    }

    @Override
    @Transactional
    public Viaje save(Viaje viaje) {
        return repositoryViaje.save(viaje);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositoryViaje.deleteById(id);
    }

    @Override
    public Page<Viaje> findAll(Pageable pageable) {
        return repositoryViaje.findAll(pageable);
    }

    @Override
    public Iterable<Viaje> findByFechaInicioGreaterThan(String fechaActual) {
        return repositoryViaje.findByFechaInicioGreaterThan(Date.valueOf(fechaActual));
    }
}
