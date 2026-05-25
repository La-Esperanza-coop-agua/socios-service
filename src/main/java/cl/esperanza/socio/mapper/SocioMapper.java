package cl.esperanza.socio.mapper;

import cl.esperanza.socio.dto.CreateSocioRequest;
import cl.esperanza.socio.model.Socio;

public class SocioMapper {
    
    public static Socio toModel(CreateSocioRequest request) {
        return new Socio(
            0, 
            request.run(), 
            request.telefono(), 
            request.nombre(), 
            request.apellido(), 
            request.direccion(), 
            request.correo()
        );
    }
}