package ctc.obligatorio.AgenciaViajesSpring.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ctc.obligatorio.AgenciaViajesSpring.entity.Imagen;

@Repository("repositoryImagen")
public interface RepositoryImagen extends JpaRepository<Imagen, Serializable> {

}
