package cl.esperanza.socio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateSocioRequest(
    @PositiveOrZero(message= "Debe ingresar un numero de telefono valido") int telefono,
    
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "El apellido es obligatorio") String apellido,
    @NotBlank(message = "La dirección no puede estar vacía") String direccion,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido") String correo
) {}
