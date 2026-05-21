package cl.esperanza.socios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.esperanza.socios.model.Socios;
import cl.esperanza.socios.repository.SociosRepository;
import java.util.List;

@Service
public class SociosService {

    @Autowired
    private SociosRepository sociosRepo;

    public List<Socios> obtenerTodos() {
        return sociosRepo.findAll();
    }

    public Socios guardarSocio(Socios socio) {
        return sociosRepo.save(socio);
    }
}