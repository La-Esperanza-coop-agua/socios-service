package cl.esperanza.socio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.esperanza.socio.dto.CreateSocioRequest;
import cl.esperanza.socio.mapper.SocioMapper;
import cl.esperanza.socio.model.Socio;
import cl.esperanza.socio.service.SocioService;
import cl.esperanza.socio.exception.ResourceNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/socios") 
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    @GetMapping
    public ResponseEntity<List<Socio>> getAllSocios() {
        return ResponseEntity.ok(socioService.obtenerTodos());
    }

    @GetMapping("/run/{run}")
    public ResponseEntity<Socio> getSocioByRun(@PathVariable String run) {
        Socio socio = socioService.obtenerPorRun(run); 
        if (socio == null) {

            throw new ResourceNotFoundException("No se encontró ningún socio con el RUN: " + run);
        }
        return ResponseEntity.ok(socio);
    }

    @GetMapping("/correo")
    public ResponseEntity<List<String>> getAllByCorreo() {
        return ResponseEntity.ok(socioService.obtenerTodosCorreos());
    }
    
    @PostMapping
    public ResponseEntity<Socio> addSocio(@Valid @RequestBody CreateSocioRequest request) {
        
        Socio nuevoSocio = socioService.guardarSocio(SocioMapper.toModel(request));
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSocio);
    }
}