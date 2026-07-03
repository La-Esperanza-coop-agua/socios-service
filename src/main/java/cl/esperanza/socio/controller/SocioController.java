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
import cl.esperanza.socio.mapper.SocioMapper;
import cl.esperanza.socio.model.Socio;
import cl.esperanza.socio.service.SocioService;
import jakarta.validation.Valid;

// Importaciones de Swagger
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/socios")
@Tag(name = "Socios", description = "Gestión de los socios de la cooperativa de agua")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService) {
        this.socioService = socioService;
    }

    // Endpoint 1
    @Operation(summary = "Obtener correos", description = "Obtiene una lista con todos los correos de los socios (para Notificaciones)")
    @ApiResponse(responseCode = "200", description = "Lista de correos obtenida exitosamente")
    @GetMapping("/correo")
    public ResponseEntity<List<String>> getAllByCorreo() {
        return ResponseEntity.ok(socioService.obtenerTodosCorreos());
    }

    // Endpoint 2
    @Operation(summary = "Registrar un nuevo socio", description = "Agrega un socio al sistema con sus datos básicos")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Socio creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados")
    })
    @PostMapping
    public ResponseEntity<Socio> addSocio(@Valid @RequestBody CreateSocioRequest request) {
        Socio nuevoSocio = socioService.guardarSocio(SocioMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSocio);
    }

    // Endpoint 3
    @Operation(summary = "Actualizar un socio", description = "Modifica la información de un socio existente usando su RUN")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Socio actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Socio no encontrado")
    })
    @PutMapping("/actualizar/{run}")
    public ResponseEntity<Socio> updateSocio(@PathVariable String run, @Valid @RequestBody UpdateSocioRequest request) {
        Socio socioActualizado = socioService.actualizarSocio(run, SocioMapper.toModel(run, request));
        return ResponseEntity.ok(socioActualizado);
    }

    // Endpoint 4
    @Operation(summary = "Validar si un socio existe", description = "Consulta si un RUN específico está registrado (Usado por Facturación e Incidencias)")
    @GetMapping("/existe/{run}")
    public ResponseEntity<Boolean> existeSocio(@PathVariable String run) {
        boolean existe = socioService.existeSocio(run);
        return ResponseEntity.ok(existe);
    }

    // Endpoint 5
    @Operation(summary = "Eliminar un socio", description = "Borra todos los registros de un socio del sistema usando su RUN")
    @ApiResponse(responseCode = "204", description = "Socio eliminado exitosamente (Sin contenido)")
    @DeleteMapping("/borrar/{run}")
    public ResponseEntity<Void> eliminarTelemetria(@PathVariable String run){
        socioService.eliminarPorRun(run);
        return ResponseEntity.noContent().build();
    }
}