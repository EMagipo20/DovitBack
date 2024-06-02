package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Foros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//-> YAN SOTO
@Repository
public interface ForosRepository extends JpaRepository<Foros, Long> {
    @Query("SELECT f.proyecto.id, COUNT(f) FROM Foros f GROUP BY f.proyecto.id")
    List<Object[]> contarForosPorProyecto();
}
