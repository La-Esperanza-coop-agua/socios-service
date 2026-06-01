package cl.esperanza.socio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.esperanza.socio.model.Socio;
import java.util.List;

@Repository
public interface SocioRepository extends JpaRepository<Socio, Integer> {
    Socio findByRun(String run);
    @Query("SELECT DISTINCT s.correo FROM Socio s")
    List<String> findAllByCorreo();
    
}