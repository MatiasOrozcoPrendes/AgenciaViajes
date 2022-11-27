package ctc.obligatorio.AgenciaViajesSpring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ctc.obligatorio.AgenciaViajesSpring.entity.Imagen;

public interface ServiceImagen {
    public Iterable<Imagen> findAll();
    public Optional<Imagen> findById(Long id);
    public Imagen save(Imagen save);
    public void deleteById(Long id);
    public Page<Imagen> findAll(Pageable pageable);
}
