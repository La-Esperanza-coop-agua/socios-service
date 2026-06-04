package cl.esperanza.socio.controller;

import java.util.List;

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

    // Endpoint 1 get a todos los correos de los socios (para Notificaciones)
    @GetMapping("/correo")
    public ResponseEntity<List<String>> getAllByCorreo() {
        return ResponseEntity.ok(socioService.obtenerTodosCorreos());
    }

    // Endpoint 2 post para agregar un socio
    @PostMapping
    public ResponseEntity<Socio> addSocio(@Valid @RequestBody CreateSocioRequest request) {
        Socio nuevoSocio = socioService.guardarSocio(SocioMapper.toModel(request));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSocio);
    }

    // Endpoint 3 put para actualizar socio por el rut
    @PutMapping("/actualizar/{run}")
    public ResponseEntity<Socio> updateSocio(@PathVariable String run, @Valid @RequestBody UpdateSocioRequest request) {
        Socio socioActualizado = socioService.actualizarSocio(run, SocioMapper.toModel(run, request));
        return ResponseEntity.ok(socioActualizado);
    }

    // Endpoint 4 Validar si un socio existe (Para Facturación e Incidencias)
    @GetMapping("/existe/{run}")
    public ResponseEntity<Boolean> existeSocio(@PathVariable String run) {
        boolean existe = socioService.existeSocio(run);
        return ResponseEntity.ok(existe);
    }

    // EndPoint 5 borrar por run
    @DeleteMapping("/borrar/{run}")
    public ResponseEntity<Void> eliminarTelemetria(@PathVariable String run){
        socioService.eliminarPorRun(run);
        return ResponseEntity.noContent().build();
    }

}