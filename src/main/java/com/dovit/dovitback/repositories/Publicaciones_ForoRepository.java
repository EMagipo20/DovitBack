package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Publicaciones_Foro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//-> HITALO
@Repository
public interface Publicaciones_ForoRepository extends JpaRepository<Publicaciones_Foro, Long> {
    @Query("SELECT pf.organizacion.id, COUNT(pf) FROM Publicaciones_Foro pf GROUP BY pf.organizacion.id")
    List<Object[]> contarPublicacionesPorOrganizacion();
}
