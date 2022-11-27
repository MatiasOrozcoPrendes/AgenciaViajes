package ctc.obligatorio.AgenciaViajesSpring.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ctc.obligatorio.AgenciaViajesSpring.entity.Usuario;

@Repository("repositoryUsuario")
public interface RepositoryUsuario extends JpaRepository<Usuario, Serializable> {

    public abstract Optional<Usuario> findByEmail(String email);
    public abstract Optional<Usuario> findByEmailAndPassword(String email, String password);
    
}
