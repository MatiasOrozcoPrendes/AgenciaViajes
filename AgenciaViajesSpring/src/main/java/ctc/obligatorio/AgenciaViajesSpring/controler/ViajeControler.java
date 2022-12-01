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

import ctc.obligatorio.AgenciaViajesSpring.entity.Viaje;
import ctc.obligatorio.AgenciaViajesSpring.service.ServiceViaje;

//Con esta anotacion le decimos a Spring que esta clase es un controlador
@RestController
//Con esta anotacion le decimos a Spring cual es la ruta base para este controlador
@RequestMapping("api/viajes")
public class ViajeControler {
    
    //Inyectamos el servicio de viaje
    @Autowired
    private ServiceViaje serviceViaje;
    
    // Crear un viaje
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Post
    @PostMapping
    // Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el JSON que viene en el body de la peticion en un objeto de tipo Viaje
    public ResponseEntity<?> create(@RequestBody Viaje viaje) {
        try {
            // Se llama al metodo save del servicio de viaje para guardar el viaje en la base de datos
            // El metodo save retorna el viaje que se guardo en la base de datos
            // El metodo save tambien se encarga de asignar un id al viaje
            // Se utiliza el metodo ResponseEntity.ok() para retornar un objeto de tipo ResponseEntity con el viaje que se guardo en la base de datos y el codigo de estado 200
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceViaje.save(viaje));
        } catch (Exception e) {
            // Si ocurre un error se retorna un objeto de tipo ResponseEntity con el mensaje de error y el codigo de estado 500
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // Buscar un viaje por id
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    @GetMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id del viaje que viene en la url
    public ResponseEntity<?> read(@PathVariable Long id) {
        // Se llama al metodo findById del servicio de viaje para buscar el viaje en la base de datos
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        // Se verifica si el viaje existe
        if (!unViaje.isPresent()) {
            // Si el viaje no existe se retorna un objeto de tipo ResponseEntity con el mensaje de error y el codigo de estado 404
            return ResponseEntity.notFound().build();
        }
        // Si el viaje existe se retorna un objeto de tipo ResponseEntity con el viaje que se encontro en la base de datos y el codigo de estado 200
        return ResponseEntity.ok(unViaje);
    }
    
    // Actualizar un viaje
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Put
    @PutMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id del viaje que viene en la url
    // Se agrega la anotacion @RequestBody para que Spring se encargue de convertir el JSON que viene en el body de la peticion en un objeto de tipo Viaje
    public ResponseEntity<?> update(@RequestBody Viaje viaje, @PathVariable Long id) {
        // Se llama al metodo findById del servicio de viaje para buscar el viaje en la base de datos
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        // Se verifica si el viaje existe
        if (!unViaje.isPresent()) {
            // Si el viaje no existe se retorna un objeto de tipo ResponseEntity con el mensaje de error y el codigo de estado 404
            return ResponseEntity.notFound().build();
        }
        // Se actualizan los datos del viaje
        unViaje.get().setDestino(viaje.getDestino());
        unViaje.get().setFechaInicio(viaje.getFechaInicio());
        unViaje.get().setModalidad(viaje.getModalidad());
        unViaje.get().setPrecio(viaje.getPrecio());
        // Se llama al metodo save del servicio de viaje para guardar el viaje en la base de datos
        // El metodo save retorna el viaje que se guardo en la base de datos
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceViaje.save(unViaje.get()));
    }

    // eliminar un viaje
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Delete
    @DeleteMapping("/{id}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener el id del viaje que viene en la url
    public ResponseEntity<?> delete(@PathVariable Long id) {
        // Se llama al metodo findById del servicio de viaje para buscar el viaje en la base de datos
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        // Se verifica si el viaje existe
        if (!unViaje.isPresent()) {
            // Si el viaje no existe se retorna un objeto de tipo ResponseEntity con el mensaje de error y el codigo de estado 404
            return ResponseEntity.notFound().build();
        }
        // Se llama al metodo delete del servicio de viaje para eliminar el viaje de la base de datos
        serviceViaje.deleteById(id);
        // Se retorna un objeto de tipo ResponseEntity con el codigo de estado 204
        return ResponseEntity.ok().build();
    }

    // Listar todos los viajes
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    @GetMapping
    public List<Viaje> readAll() {
        // Se llama al metodo findAll del servicio de viaje para obtener todos los viajes de la base de datos
        List<Viaje> viajes = StreamSupport
                .stream(serviceViaje.findAll().spliterator(), false)
                // Se utiliza el metodo collect para convertir el Stream en una lista
                .collect(Collectors.toList());
        // Se retorna la lista de viajes        
        return viajes;
    }

    // Listar todos los viajes con fecha de inicio mayor a la fecha recibida por parametro
    // Con esta anotacion le decimos a Spring que este metodo es un metodo de tipo Get
    // Recibe por parametro la fecha que se quiere utilizar para filtrar los viajes
    @GetMapping("/fechaInicio/{fecha}")
    // Se agrega la anotacion @PathVariable para que Spring se encargue de obtener la fecha que viene en la url
    public List<Viaje> readAllByFechaInicioGreaterThan(@PathVariable String fecha) {
        // Se llama al metodo findAllByFechaInicioGreaterThan del servicio de viaje para obtener todos los viajes de la base de datos con fecha de inicio mayor a la fecha recibida por parametro
        List<Viaje> viajes = StreamSupport
                .stream(serviceViaje.findByFechaInicioGreaterThan(fecha).spliterator(), false)
                // Se utiliza el metodo collect para convertir el Stream en una lista
                .collect(Collectors.toList());
        // Se retorna la lista de viajes
        return viajes;
        
    }
}
