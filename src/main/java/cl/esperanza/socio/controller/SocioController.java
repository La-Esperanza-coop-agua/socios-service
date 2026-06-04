package cl.esperanza.socio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.esperanza.socio.dto.CreateSocioRequest;
import cl.esperanza.socio.dto.UpdateSocioRequest;
import cl.esperanza.socio.exception.ResourceNotFoundException;
import cl.esperanza.socio.mapper.SocioMapper;
import cl.esperanza.socio.model.Socio;
import cl.esperanza.socio.service.SocioService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/socios") 
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }
    // Endpoint 1 get a todos los socios
    @GetMapping
    public ResponseEntity<List<Socio>> getAllSocios() {
        return ResponseEntity.ok(socioService.obtenerTodos());
    }
    // Endpoint 2 get a un socio por un rut en especifico
    @GetMapping("/run/{run}")
    public ResponseEntity<Socio> getSocioByRun(@PathVariable String run) {
        Socio socio = socioService.obtenerPorRun(run); 

        if (socio == null) {
            throw new ResourceNotFoundException("No se encontró ningún socio con el RUN: " + run);
        }
        
        return ResponseEntity.ok(socio);
    }
    // Endpoint 3 get a todos los correos de los socios
    @GetMapping("/correo")
    public ResponseEntity<List<String>> getAllByCorreo() {
        return ResponseEntity.ok(socioService.obtenerTodosCorreos());
    }
    // Endpoint 4 post para agregar un socio
    @PostMapping
    public ResponseEntity<Socio> addSocio(@Valid @RequestBody CreateSocioRequest request) {
        Socio nuevoSocio = socioService.guardarSocio(SocioMapper.toModel(request));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSocio);
    }
    // Endpoint 5 put para actualizar socio por el rut
    @PutMapping("/actualizar/{run}")
    public ResponseEntity<Socio> updateSocio(@PathVariable String run, @Valid @RequestBody UpdateSocioRequest request) {
        Socio socioActualizado = socioService.actualizarSocio(run, SocioMapper.toModel(run, request));
        return ResponseEntity.ok(socioActualizado);
    }

    // Endpoint 6: Validar si un socio existe (Para Facturación e Incidencias)
    @GetMapping("/existe/{run}")
    public ResponseEntity<Boolean> existeSocio(@PathVariable String run) {
        boolean existe = socioService.existeSocio(run);
        return ResponseEntity.ok(existe);
    }

}