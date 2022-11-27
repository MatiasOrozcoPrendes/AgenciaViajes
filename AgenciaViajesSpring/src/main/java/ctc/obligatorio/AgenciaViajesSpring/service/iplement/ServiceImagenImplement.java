package ctc.obligatorio.AgenciaViajesSpring.service.iplement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctc.obligatorio.AgenciaViajesSpring.entity.Imagen;
import ctc.obligatorio.AgenciaViajesSpring.repository.RepositoryImagen;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceImagen;

@Service("serviceImagen")
public class ServiceImagenImplement implements ServiceImagen {

    @Autowired
    private RepositoryImagen repositoryImagen;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Imagen> findAll() {
        return repositoryImagen.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Imagen> findById(Long id) {
        return repositoryImagen.findById(id);
    }

    @Override
    @Transactional
    public Imagen save(Imagen imagen) {
        return repositoryImagen.save(imagen);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositoryImagen.deleteById(id);
    }

    @Override
    public Page<Imagen> findAll(Pageable pageable) {
        return repositoryImagen.findAll(pageable);
    }

}
