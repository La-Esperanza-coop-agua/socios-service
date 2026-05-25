package cl.esperanza.socio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateSocioRequest(
    @NotBlank(message = "El RUN no puede estar vacío")

    @Size(min = 8, max = 13, message = "El RUN debe tener entre 8 y 13 caracteres")

    @Pattern(regexp = "^\\d{7,8}-[0-9Kk]$", message = "El RUN debe tener un formato válido con guion (ej: 12345678-9)")
    String run,
    int telefono,
    
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "El apellido es obligatorio")
    String apellido,

    @NotBlank(message = "La dirección no puede estar vacía")
    String direccion,

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo no es válido")
    String correo
) {}