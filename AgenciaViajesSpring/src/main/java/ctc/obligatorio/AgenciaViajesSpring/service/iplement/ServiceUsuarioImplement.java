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

//Se utiliza la anotacion @Service para indicar que esta clase es un servicio
@Service("serviceUsuario")
//Esta clase implementa la interfaz ServiceUsuario
public class ServiceUsuarioImplement implements ServiceUsuario {

    //Se utiliza la anotacion @Autowired para que se inyecte la dependencia de la interfaz RepositoryUsuario
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    
    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Iterable con todos los usuarios
    public Iterable<Usuario> findAll() {
        return repositoryUsuario.findAll();
    }

    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Usuario con el usuario que tenga el id que se le pasa por parametro si existe
    public Optional<Usuario> findById(Long id) {
        return repositoryUsuario.findById(id);
    }

    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Usuario con el usuario que tenga el email que se le pasa por parametro si existe
    public Optional<Usuario> findByEmail(String email) {
        return repositoryUsuario.findByEmail(email);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que guarda un usuario en la base de datos
    public Usuario save(Usuario user) {
        return repositoryUsuario.save(user);
    }

    @Override
    //Se utiliza la anotacion @Transactional para indicar que se va a realizar una transaccion en la base de datos
    @Transactional
    //Metodo que elimina un usuario de la base de datos
    public void deleteById(Long id) {
        repositoryUsuario.deleteById(id);
    }

    @Override
    //Se devuelve un Page de Usuario con todos los usuarios que se encuentran en la base de datos
    public Page<Usuario> findAll(Pageable pageable) {
        return repositoryUsuario.findAll(pageable);
    }
    
    @Override
    //Se utiliza la anotacion @Transactional para que solo sea una lectura de la base de datos
    @Transactional(readOnly = true)
    //Metodo que retorna un Optional de Usuario con el usuario que tenga el email y password que se le pasa por parametro si existe
    public Optional<Usuario> findByEmailAndPassword(String email, String password) {
        return repositoryUsuario.findByEmailAndPassword(email, password);
    }
    
}
