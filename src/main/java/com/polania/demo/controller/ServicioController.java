package com.polania.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polania.demo.entities.Servicio;
import com.polania.demo.repository.ServicioRepository;

@RestController
@CrossOrigin(origins = "*") 
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioController(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    /**
     * Obtiene todos los servicios.
     * @return ResponseEntity con la lista de servicios y el código de estado correspondiente.
     */
    @GetMapping
    public ResponseEntity<List<Servicio>> getAllServicios() {
        List<Servicio> servicios = servicioRepository.findAll();
        return ResponseEntity.ok(servicios);
    }

    /**
     * Obtiene un servicio por su ID.
     * @param id El ID del servicio a buscar.
     * @return ResponseEntity con el servicio encontrado y el código de estado correspondiente, o NOT_FOUND si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Integer id) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(id);
        return optionalServicio.map(servicio -> ResponseEntity.ok(servicio)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo servicio.
     * @param servicio El servicio a crear.
     * @return ResponseEntity con el servicio creado y el código de estado CREATED, junto con la URI del nuevo recurso.
     */
    @PostMapping("/save")
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio) {
        Servicio newServicio = servicioRepository.save(servicio);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newServicio.getId()).toUri();
        return ResponseEntity.created(location).body(newServicio);
    }

    /**
     * Actualiza un servicio existente.
     * @param id El ID del servicio a actualizar.
     * @param updatedServicio Los nuevos datos del servicio.
     * @return ResponseEntity con el servicio actualizado y el código de estado correspondiente, o NOT_FOUND si no se encuentra.
     */
    @PutMapping("save/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Integer id, @RequestBody Servicio updatedServicio) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(id);
        if (optionalServicio.isPresent()) {
            updatedServicio.setId(id);
            Servicio savedServicio = servicioRepository.save(updatedServicio);
            return ResponseEntity.ok(savedServicio);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un servicio existente.
     * @param id El ID del servicio a eliminar.
     * @return ResponseEntity con el código de estado correspondiente, NO_CONTENT si se eliminó correctamente, o NOT_FOUND si no se encuentra.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Integer id) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(id);
        if (optionalServicio.isPresent()) {
            servicioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

