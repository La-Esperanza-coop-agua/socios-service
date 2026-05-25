package cl.esperanza.socio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.esperanza.socio.model.Socio;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {
    Socio findByRun(String run);
    
}