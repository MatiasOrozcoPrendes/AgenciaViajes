package ctc.obligatorio.AgenciaViajesSpring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ctc.obligatorio.AgenciaViajesSpring.entity.Usuario;

public interface ServiceUsuario {
    public Iterable<Usuario> findAll();
    public Optional<Usuario> findById(Long id);
    public Optional<Usuario> findByEmail(String email);
    public Usuario save(Usuario save);
    public void deleteById(Long id);
    public Page<Usuario> findAll(Pageable pageable);
    public Optional<Usuario> findByEmailAndPassword(String email, String password);
}
