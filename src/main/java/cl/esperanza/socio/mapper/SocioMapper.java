package cl.esperanza.socio.mapper;

import cl.esperanza.socio.dto.CreateSocioRequest;
import cl.esperanza.socio.dto.UpdateSocioRequest;
import cl.esperanza.socio.model.Socio;

public class SocioMapper {
    
    public static Socio toModel(CreateSocioRequest request){
        return new Socio(null, 
            request.run(), request.telefono(), request.nombre(), 
            request.apellido(), request.direccion(), request.correo()
        );
    }

    public static Socio toModel(String runUrl, UpdateSocioRequest request){
        return new Socio(null,
            runUrl, request.telefono(), request.nombre(),
            request.apellido(), request.direccion(), request.correo()            
        );
    }
}