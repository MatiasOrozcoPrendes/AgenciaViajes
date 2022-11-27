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

@RestController
@RequestMapping("api/viajes")
public class ViajeControler {
    
    @Autowired
    private ServiceViaje serviceViaje;
    
    // create viaje
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Viaje viaje) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceViaje.save(viaje));
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    // find by Id
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        if (!unViaje.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(unViaje);
    }
    
    // update viaje
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Viaje viaje, @PathVariable Long id) {
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        if (!unViaje.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        unViaje.get().setDestino(viaje.getDestino());
        unViaje.get().setFechaInicio(viaje.getFechaInicio());
        unViaje.get().setModalidad(viaje.getModalidad());
        unViaje.get().setPrecio(viaje.getPrecio());
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceViaje.save(unViaje.get()));
    }

    // delete viaje
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Viaje> unViaje = serviceViaje.findById(id);
        if (!unViaje.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        serviceViaje.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // find all viajes
    @GetMapping
    public List<Viaje> readAll() {
        List<Viaje> viajes = StreamSupport
                .stream(serviceViaje.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return viajes;
        
    }

    // find all viajes with fechaInicio greater than fechaActual
    @GetMapping("/fechaInicio/{fechaActual}")
    public List<Viaje> readAllByFechaInicioGreaterThan(@PathVariable String fechaActual) {
        List<Viaje> viajes = StreamSupport
                .stream(serviceViaje.findByFechaInicioGreaterThan(fechaActual).spliterator(), false)
                .collect(Collectors.toList());
        return viajes;
        
    }
}
