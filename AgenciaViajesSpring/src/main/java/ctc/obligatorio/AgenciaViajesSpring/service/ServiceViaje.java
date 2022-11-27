package ctc.obligatorio.AgenciaViajesSpring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;

public interface ServiceViaje {
    public Iterable<Viaje> findAll();
    public Optional<Viaje> findById(Long id);
    public Viaje save(Viaje save);
    public void deleteById(Long id);
    public Page<Viaje> findAll(Pageable pageable);
    public Iterable<Viaje> findByFechaInicioGreaterThan(String fechaActual);
}