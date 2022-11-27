package ctc.obligatorio.AgenciaViajesSpring.service.iplement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctc.obligatorio.AgenciaViajesSpring.entity.Usuario;
import ctc.obligatorio.AgenciaViajesSpring.repository.RepositoryUsuario;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceUsuario;

@Service("serviceUsuario")
public class ServiceUsuarioImplement implements ServiceUsuario {

    @Autowired
    private RepositoryUsuario repositoryUsuario;
    
    @Override
    @Transactional(readOnly = true)
    public Iterable<Usuario> findAll() {
        return repositoryUsuario.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repositoryUsuario.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmail(String email) {
        return repositoryUsuario.findByEmail(email);
    }

    @Override
    @Transactional
    public Usuario save(Usuario user) {
        return repositoryUsuario.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repositoryUsuario.deleteById(id);
    }

    @Override
    public Page<Usuario> findAll(Pageable pageable) {
        return repositoryUsuario.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByEmailAndPassword(String email, String password) {
        return repositoryUsuario.findByEmailAndPassword(email, password);
    }
    
}
