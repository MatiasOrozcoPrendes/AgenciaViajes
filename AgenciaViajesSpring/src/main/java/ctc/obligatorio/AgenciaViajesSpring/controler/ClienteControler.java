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

//Con esta anotacion le decimos a Spring que esta clase es un controlador
@RestController
//Con esta anotacion le decimos a Spring cual es la ruta base para este controlador
@RequestMapping("api/clientes")
public class ClienteControler {

    //Inyectamos el servicio de cliente
    @Autowired
    private ServiceCliente ServiceCliente;
    
    //Crear un cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Post
    @PostMapping
    //Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el json que recibimos en un objeto de tipo Cliente
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            //Llamamos al metodo save del servicio de cliente y le pasamos el cliente que recibimos por parametro
            //El metodo save retorna el cliente que se guardo en la base de datos
            //El metodo save tambien se encarga de asignarle un id al cliente
            //Se utiliza ResponseEntity para devolver el cliente que se guardo en la base de datos
            return ResponseEntity.status(HttpStatus.CREATED).body(ServiceCliente.save(cliente));
        } catch (Exception e) {
            //Si ocurre un error se retorna un ResponseEntity con el mensaje de error y el codigo de error 500
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    //Buscar un cliente por id
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    @GetMapping("/{id}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    public ResponseEntity<?> read(@PathVariable Long id) {
        //Llamamos al metodo findById del servicio de cliente y le pasamos el id que recibimos por parametro.
        //El metodo findById retorna un Optional de tipo Cliente
        Optional<Cliente> unCliente = ServiceCliente.findById(id);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el id que recibimos por parametro
        if (!unCliente.isPresent()) {
            //Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se retorna un ResponseEntity con el cliente que se encontro y el codigo de 200
        return ResponseEntity.ok(unCliente);
    }

    //Buscar un cliente por email
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    @GetMapping("/email/{email}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el email que recibimos en un objeto de tipo String
    public ResponseEntity<?> readByEmail(@PathVariable String email) {
        //Llamamos al metodo findByEmail del servicio de cliente y le pasamos el email que recibimos por parametro.
        //El metodo findByEmail retorna un Optional de tipo Cliente
        Optional<Cliente> unCliente = ServiceCliente.findByEmail(email);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el email que recibimos por parametro
        if (!unCliente.isPresent()) {
            // Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "El correo no esta registrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        //Si el Optional no esta vacio se retorna un ResponseEntity con el cliente que se encontro y el codigo 200
        return ResponseEntity.ok(unCliente);
    }

    //Actualizar un cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Put
    @PutMapping("/{id}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    //Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el json que recibimos en un objeto de tipo Cliente
    public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
        //Llamamos al metodo findById del servicio de cliente y le pasamos el id que recibimos por parametro.
        //El metodo findById retorna un Optional de tipo Cliente
        Optional<Cliente> unCliente = ServiceCliente.findById(id);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el id que recibimos por parametro
        if (!unCliente.isPresent()) {
            //Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se actualiza el cliente que se encontro con los datos que recibimos por parametro
        unCliente.get().setCi(cliente.getCi());
        unCliente.get().setNombre(cliente.getNombre());
        unCliente.get().setApellido(cliente.getApellido());
        unCliente.get().setEmail(cliente.getEmail());
        unCliente.get().setPassword(cliente.getPassword());
        //Llamamos al metodo save del servicio de cliente y le pasamos el cliente que se actualizo
        return ResponseEntity.status(HttpStatus.CREATED).body(ServiceCliente.save(unCliente.get()));
    }
    
    // Eliminar un cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Delete
    @DeleteMapping("/{id}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    public ResponseEntity<?> delete(@PathVariable Long id) {
        //Llamamos al metodo findById del servicio de cliente y le pasamos el id que recibimos por parametro.
        //El metodo findById retorna un Optional de tipo Cliente
        Optional<Cliente> user = ServiceCliente.findById(id);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el id que recibimos por parametro
        if (!user.isPresent()) {
            //Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se llama al metodo delete del servicio de cliente y le pasamos el cliente que se encontro
        ServiceCliente.deleteById(id);
        //Se retorna un ResponseEntity con el codigo 204 que significa que la peticion se completo con exito
        return ResponseEntity.ok().build();
    }
    
    // Listar todos los clientes
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    @GetMapping
    public List<Cliente> readAll() {
        //Llamamos al metodo findAll del servicio de cliente
        //El metodo findAll retorna una lista de tipo Cliente
        List<Cliente> users = StreamSupport
                //Se filtra la lista para que solo se muestren los clientes que no esten eliminados logicamente (eliminado = false)
                .stream(ServiceCliente.findAll().spliterator(), false)
                //Se setea la respuesta de la consulta en una lista de tipo Cliente
                .collect(Collectors.toList());
                //Se retorna la lista de clientes
                return users;
    }

    // agregar un viaje a la lista de viajes del cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Post
    //Recibe el id del cliente y el id del viaje
    @PutMapping("/agregarViaje/{id}/{idViaje}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    public ResponseEntity<?> agregarViaje(@PathVariable Long id, @PathVariable Long idViaje) {
        try {
            //Llamamos al metodo addViaje del servicio de cliente y le pasamos el id del cliente y el id del viaje
            ServiceCliente.addViaje(id, idViaje);
            //Se retorna un ResponseEntity con el codigo 201 que significa que la peticion se completo con exito
            return ResponseEntity.status(HttpStatus.CREATED).body("Viaje agregado");
        } catch (Exception e) {
            //Si se produce un error se retorna un ResponseEntity con el mensaje de error y el codigo de error 400
            System.out.println("Error: " + e.getMessage());
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // eliminar un viaje de la lista de viajes del cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Put
    //Recibe el id del cliente y el id del viaje
    @PutMapping("/eliminarViaje/{id}/{idViaje}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    public ResponseEntity<?> eliminarViaje(@PathVariable Long id, @PathVariable Long idViaje) {
        try {
            //Llamamos al metodo removeViaje del servicio de cliente y le pasamos el id del cliente y el id del viaje
            ServiceCliente.removeViaje(id, idViaje);
            //Se retorna un ResponseEntity con el codigo 201 que significa que la peticion se completo con exito
            return ResponseEntity.status(HttpStatus.CREATED).body("Viaje eliminado");
        } catch (Exception e) {
            //Si se produce un error se retorna un ResponseEntity con el mensaje de error y el codigo de error 400
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // listar los viajes del cliente
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    @GetMapping("/listarViajes/{id}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long
    public ResponseEntity<?> listarViajes(@PathVariable Long id) {
        //Llamamos al metodo findById del servicio de cliente y le pasamos el id que recibimos por parametro.
        Optional<Cliente> unCliente = ServiceCliente.findById(id);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el id que recibimos por parametro
        if (!unCliente.isPresent()) {
            //Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        }
        //Si el Optional no esta vacio se llama al metodo getViajes del servicio de cliente y le pasamos el cliente que se encontro
        return ResponseEntity.ok(unCliente.get().getViajes());
    }

    // devolver el primier viaje del cliente despues de una fecha dada
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    @GetMapping("/primerViaje/{id}/{fecha}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el id que recibimos en un objeto de tipo Long y la fecha en un String
    public ResponseEntity<?> primerViaje(@PathVariable Long id, @PathVariable String fecha) {
        //Llamamos al metodo primerViajeDespuesDeFecha del servicio de cliente y le pasamos el id que recibimos por parametro y la fecha
        Viaje unViaje = ServiceCliente.primerViajeDespuesDeFecha(id, fecha);
        //Si el viaje es null quiere decir que no se encontro un viaje despues de la fecha que recibimos por parametro
        if (unViaje == null) {
            //Si el viaje es null se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        }
        //Si el viaje no es null se retorna un ResponseEntity con el viaje y el codigo de respuesta 200
        return ResponseEntity.ok(unViaje);
    }

    // devolver un cliente registrado con un email y password
    //Con esta anotacion le decimos a Spring que este metodo es un Get
    //Recibe el email y el password
    @GetMapping("/login/{email}/{password}")
    //Se agrega la anotacion @PathVariable para que Spring se encargue de convertir el email y el password en un String
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String password) {
        //Llamamos al metodo findByEmailAndPassword del servicio de cliente y le pasamos el email y el password que recibimos por parametro
        Optional<Cliente> unCliente = ServiceCliente.findByEmailAndPassword(email, password);
        //Si el Optional esta vacio quiere decir que no se encontro un cliente con el email y el password que recibimos por parametro
        if (!unCliente.isPresent()) {
            //Si el Optional esta vacio se retorna un ResponseEntity con el mensaje de error y el codigo de error 404
            return ResponseEntity.notFound().build();
        } else {
            //Si el Optional no esta vacio se retorna un ResponseEntity con el cliente y el codigo de respuesta 200
            return ResponseEntity.ok(unCliente);
        }
    }
   }

