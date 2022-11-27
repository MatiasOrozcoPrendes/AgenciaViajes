package ctc.obligatorio.AgenciaViajesSpring.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ctc.obligatorio.AgenciaViajesSpring.entity.Usuario;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceUsuario;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioControler {

    @Autowired
    private ServiceUsuario serviceUsuario;

    // create usuario
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.save(usuario));
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // find by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Usuario> unUsuario = serviceUsuario.findById(id);
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unUsuario);
    }

    // find by email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> readByEmail(@PathVariable String email) {
        Optional<Usuario> unUsuario = serviceUsuario.findByEmail(email);
        if (!unUsuario.isPresent()) {
            // creo un json con el error
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "El correo no esta registrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        return ResponseEntity.ok(unUsuario);
    }

    // update usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Optional<Usuario> unUsuario = serviceUsuario.findById(id);
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        unUsuario.get().setCi(usuario.getCi());
        unUsuario.get().setNombre(usuario.getNombre());
        unUsuario.get().setApellido(usuario.getApellido());
        unUsuario.get().setEmail(usuario.getEmail());
        unUsuario.get().setPassword(usuario.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.save(unUsuario.get()));
    }

    // delete usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Usuario> user = serviceUsuario.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceUsuario.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // find all 
    @GetMapping
    public List<Usuario> readAll() {
        List<Usuario> users = StreamSupport
                .stream(serviceUsuario.findAll().spliterator(), false)
                .collect(Collectors.toList());
                return users;
    }

    // find by email and password
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<?> readByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        Optional<Usuario> unUsuario = serviceUsuario.findByEmailAndPassword(email, password);
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(unUsuario);
        }
    }
    
}
