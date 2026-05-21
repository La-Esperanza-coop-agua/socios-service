package cl.esperanza.socios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.esperanza.socios.model.Socios;
import cl.esperanza.socios.service.SociosService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/socios")
public class SociosController {

    @Autowired
    private SociosService sociosService;

    @GetMapping
    public ResponseEntity<List<Socios>> listarSocios() {
        return ResponseEntity.ok(sociosService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<Socios> crearSocio(@RequestBody Socios socio) {
        Socios nuevoSocio = sociosService.guardarSocio(socio);
        return new ResponseEntity<>(nuevoSocio, HttpStatus.CREATED);
    }
}