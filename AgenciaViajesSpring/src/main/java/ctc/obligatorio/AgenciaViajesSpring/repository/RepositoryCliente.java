package ctc.obligatorio.AgenciaViajesSpring.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ctc.obligatorio.AgenciaViajesSpring.entity.Cliente;


@Repository("repositoryCliente")
public interface RepositoryCliente extends JpaRepository<Cliente, Serializable> {

    public abstract Optional<Cliente> findByEmail(String email);
    public abstract Optional<Cliente> findByEmailAndPassword(String email, String password);
    
}
