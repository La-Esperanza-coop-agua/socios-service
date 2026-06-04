package cl.esperanza.socio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.esperanza.socio.model.Socio;
import cl.esperanza.socio.repository.SocioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SocioService {
    @Autowired
    private SocioRepository socioRepo;

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

    public Socio actualizarSocio(String run, Socio socioActualizado){
        socioActualizado.setRun(run);
        return socioRepo.save(socioActualizado);
    }

    public boolean existeSocio(String run) {
        return socioRepo.findByRun(run) != null;
    }
}
