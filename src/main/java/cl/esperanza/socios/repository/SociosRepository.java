package cl.esperanza.socios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.esperanza.socios.model.Socios;

@Repository
public interface SociosRepository extends JpaRepository<Socios, Integer> {
    Socios findByRun(String run);
}