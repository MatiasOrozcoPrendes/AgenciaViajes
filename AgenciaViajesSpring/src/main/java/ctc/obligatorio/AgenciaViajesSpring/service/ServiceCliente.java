package ctc.obligatorio.AgenciaViajesSpring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ctc.obligatorio.AgenciaViajesSpring.entity.Cliente;
import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;

public interface ServiceCliente {
    public Iterable<Cliente> findAll();
    public Optional<Cliente> findById(Long id);
    public Optional<Cliente> findByEmail(String email);
    public Cliente save(Cliente save);
    public void deleteById(Long id);
    public Page<Cliente> findAll(Pageable pageable);
    public Cliente addViaje(Long idCliente, Long idViaje);
    public Cliente removeViaje(Long idCliente, Long idViaje);
    public Viaje primerViajeDespuesDeFecha (Long idCliente, String fecha);
    public Optional<Cliente> findByEmailAndPassword(String email, String password);

}
