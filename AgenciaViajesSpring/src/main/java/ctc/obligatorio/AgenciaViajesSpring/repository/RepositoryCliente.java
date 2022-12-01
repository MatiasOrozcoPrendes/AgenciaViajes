package ctc.obligatorio.AgenciaViajesSpring.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ctc.obligatorio.AgenciaViajesSpring.entity.Cliente;

//Se utiliza la anotacion @Repository para indicar que esta clase es un repositorio
@Repository("repositoryCliente")
//Esta interfaz extiende de JpaRepository para que se pueda utilizar los metodos de esta interfaz
public interface RepositoryCliente extends JpaRepository<Cliente, Serializable> {

    // Aqui se definen los metodos que se van a utilizar para acceder a la base de datos y que no estan definidos en la interfaz JpaRepository
    public abstract Optional<Cliente> findByEmail(String email);
    public abstract Optional<Cliente> findByEmailAndPassword(String email, String password);
    
}
