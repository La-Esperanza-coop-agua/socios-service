package cl.esperanza.socio.service;

import org.springframework.stereotype.Service;
import cl.esperanza.socio.model.Socio;
import cl.esperanza.socio.repository.SocioRepository;

import java.util.List;

@Service
public class SocioService {

    private final SocioRepository socioRepo;

    public SocioService(SocioRepository socioRepo) {
        this.socioRepo = socioRepo;
    }

    public List<Socio> obtenerTodos() {
        return socioRepo.findAll();
    }

    public Socio obtenerPorRun(String run) {
        return socioRepo.findByRun(run);
    }

    public Socio guardarSocio(Socio socio) {
        return socioRepo.save(socio);
    }
    public List<String> obtenerTodosCorreos() {
        return socioRepo.findAllByCorreo();
    }
}
