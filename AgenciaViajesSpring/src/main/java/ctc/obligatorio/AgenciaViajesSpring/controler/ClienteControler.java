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

import ctc.obligatorio.AgenciaViajesSpring.entity.Cliente;
import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceCliente;

@RestController
@RequestMapping("api/clientes")
public class ClienteControler {

    @Autowired
    private ServiceCliente ServiceCliente;
    
        // create cliente
        @PostMapping
        public ResponseEntity<?> create(@RequestBody Cliente cliente) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(ServiceCliente.save(cliente));
            } catch (Exception e) {
                HashMap<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        }
    
        // find by Id
        @GetMapping("/{id}")
        public ResponseEntity<?> read(@PathVariable Long id) {
            Optional<Cliente> unCliente = ServiceCliente.findById(id);
            if (!unCliente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(unCliente);
        }

        // find by email
        @GetMapping("/email/{email}")
        public ResponseEntity<?> readByEmail(@PathVariable String email) {
            Optional<Cliente> unCliente = ServiceCliente.findByEmail(email);
            if (!unCliente.isPresent()) {
                // creo un json con el error
                HashMap<String, String> error = new HashMap<>();
                error.put("error", "El correo no esta registrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            return ResponseEntity.ok(unCliente);
        }

        // update cliente
        @PutMapping("/{id}")
        public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
            Optional<Cliente> unCliente = ServiceCliente.findById(id);
            if (!unCliente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            unCliente.get().setCi(cliente.getCi());
            unCliente.get().setNombre(cliente.getNombre());
            unCliente.get().setApellido(cliente.getApellido());
            unCliente.get().setEmail(cliente.getEmail());
            unCliente.get().setPassword(cliente.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(ServiceCliente.save(unCliente.get()));
        }
    
        // delete cliente
        @DeleteMapping("/{id}")
        public ResponseEntity<?> delete(@PathVariable Long id) {
            Optional<Cliente> user = ServiceCliente.findById(id);
            if (!user.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            ServiceCliente.deleteById(id);
            return ResponseEntity.ok().build();
        }
    
        // find all 
        @GetMapping
        public List<Cliente> readAll() {
            List<Cliente> users = StreamSupport
                    .stream(ServiceCliente.findAll().spliterator(), false)
                    .collect(Collectors.toList());
                    return users;
        }

        // agregar un viaje a la lista de viajes del cliente
        @PutMapping("/agregarViaje/{id}/{idViaje}")
        public ResponseEntity<?> agregarViaje(@PathVariable Long id, @PathVariable Long idViaje) {
            try {
                ServiceCliente.addViaje(id, idViaje);
                return ResponseEntity.status(HttpStatus.CREATED).body("Viaje agregado");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                HashMap<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        }

        // eliminar un viaje de la lista de viajes del cliente
        @PutMapping("/eliminarViaje/{id}/{idViaje}")
        public ResponseEntity<?> eliminarViaje(@PathVariable Long id, @PathVariable Long idViaje) {
            try {
                System.out.println("id: " + id + " idViaje: " + idViaje);
                ServiceCliente.removeViaje(id, idViaje);
                return ResponseEntity.status(HttpStatus.CREATED).body("Viaje eliminado");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                HashMap<String, String> error = new HashMap<>();
                error.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        }

        // listar los viajes del cliente
        @GetMapping("/listarViajes/{id}")
        public ResponseEntity<?> listarViajes(@PathVariable Long id) {
            Optional<Cliente> unCliente = ServiceCliente.findById(id);
            if (!unCliente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(unCliente.get().getViajes());
        }

        // devolver el primier viaje del cliente despues de una fecha dada
        @GetMapping("/primerViaje/{id}/{fecha}")
        public ResponseEntity<?> primerViaje(@PathVariable Long id, @PathVariable String fecha) {
            Viaje unViaje = ServiceCliente.primerViajeDespuesDeFecha(id, fecha);
            if (unViaje == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(unViaje);
        }

        // devolver un cliente registrado con un email y password
        @GetMapping("/login/{email}/{password}")
        public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
            Optional<Cliente> unCliente = ServiceCliente.findByEmailAndPassword(email, password);
            if (!unCliente.isPresent()) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.ok(unCliente);
            }
        }
   }

