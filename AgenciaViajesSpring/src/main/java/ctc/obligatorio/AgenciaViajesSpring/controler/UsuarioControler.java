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

//Con esta anotacion le decimos a Spring que esta clase es un controlador
@RestController
//Con esta anotacion le decimos a Spring cual es la ruta base para este controlador
@RequestMapping("api/usuarios")
public class UsuarioControler {

    //Inyectamos el servicio de usuario
    @Autowired
    private ServiceUsuario serviceUsuario;

    // crear un usuario
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Post
    @PostMapping
    // Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el JSON que viene en el body de la peticion en un objeto de tipo Usuario
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            //Se llama al metodo save del servicio de usuario
            //El metodo save del servicio de usuario devuelve el objeto que se guardo en la base de datos
            //El metodo save se encarga de asignar un id al usuario que se esta guardando
            //Se utiliza el metodo ResponseEntity.ok() para devolver un codigo 200 y el objeto que se guardo en la base de datos
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.save(usuario));
        } catch (Exception e) {
            //Si ocurre un error se devuelve un codigo 500 y el mensaje del error
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Buscar un usuario por id
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    @GetMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id que viene en la url
    public ResponseEntity<?> read(@PathVariable Long id) {
        //Se llama al metodo findById del servicio de usuario, se le pasa el id que viene en la url y se guarda el resultado en un Optional
        Optional<Usuario> unUsuario = serviceUsuario.findById(id);
        //Si el Optional esta vacio se devuelve un codigo 404
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se devuelve un codigo 200 y el objeto que se encontro
        return ResponseEntity.ok(unUsuario);
    }

    // Buscar un usuario por email
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    @GetMapping("/email/{email}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el email que viene en la url
    public ResponseEntity<?> readByEmail(@PathVariable String email) {
        //Se llama al metodo findByEmail del servicio de usuario, se le pasa el email que viene en la url y se guarda el resultado en un Optional
        Optional<Usuario> unUsuario = serviceUsuario.findByEmail(email);
        //Si el Optional esta vacio se devuelve un codigo 404
        if (!unUsuario.isPresent()) {
            // creo un json con el error
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "El correo no esta registrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        //Si el Optional no esta vacio se devuelve un codigo 200 y el objeto que se encontro
        return ResponseEntity.ok(unUsuario);
    }

    // Actualizar un usuario
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Put
    @PutMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id que viene en la url
    // Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el JSON que viene en el body de la peticion en un objeto de tipo Usuario
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
        //Se llama al metodo findById del servicio de usuario, se le pasa el id que viene en la url y se guarda el resultado en un Optional
        Optional<Usuario> unUsuario = serviceUsuario.findById(id);
        //Si el Optional esta vacio se devuelve un codigo 404
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se actualiza el usuario que se encontro
        unUsuario.get().setCi(usuario.getCi());
        unUsuario.get().setNombre(usuario.getNombre());
        unUsuario.get().setApellido(usuario.getApellido());
        unUsuario.get().setEmail(usuario.getEmail());
        unUsuario.get().setPassword(usuario.getPassword());
        //Se llama al metodo save del servicio de usuario, se le pasa el usuario que se actualizo y devuelve uun ResponseEntity con el codigo 200 y el objeto que se guardo en la base de datos
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceUsuario.save(unUsuario.get()));
    }

    // Eliminar un usuario
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Delete
    @DeleteMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id que viene en la url
    public ResponseEntity<?> delete(@PathVariable Long id) {
        //Se llama al metodo findById del servicio de usuario, se le pasa el id que viene en la url y se guarda el resultado en un Optional
        Optional<Usuario> user = serviceUsuario.findById(id);
        //Si el Optional esta vacio se devuelve un codigo 404
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se llama al metodo delete del servicio de usuario, se le pasa el usuario que se encontro y se devuelve un ResponseEntity con el codigo 200
        serviceUsuario.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Listar todos los usuarios
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    @GetMapping
    public List<Usuario> readAll() {
        //Se llama al metodo findAll del servicio de usuario y se devuelve la lista de usuarios que se encontro
        List<Usuario> users = StreamSupport
                .stream(serviceUsuario.findAll().spliterator(), false)
                .collect(Collectors.toList());
                return users;
    }

    // devolver un usuario por su correo y contraseña
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    // Resivimos un email y una contraseña
    @GetMapping("/login/{email}/{password}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el email y la contraseña que vienen en la url
    public ResponseEntity<?> readByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        //Se llama al metodo findByEmailAndPassword del servicio de usuario, se le pasa el email y la contraseña que vienen en la url y se guarda el resultado en un Optional
        Optional<Usuario> unUsuario = serviceUsuario.findByEmailAndPassword(email, password);
        //Si el Optional esta vacio se devuelve un codigo 404
        if (!unUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            //Si el Optional no esta vacio se devuelve un codigo 200 y el objeto que se encontro
            return ResponseEntity.ok(unUsuario);
        }
    }
    
}
