package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Comentarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//-> DIEGO
@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long>{
    //-> Comentarios segun proyecto id
    @Query("SELECT c FROM Comentarios c WHERE c.proyecto.id = :idProyecto")
    List<Comentarios> findAllByProyectoId(Long idProyecto);

    Long countByProyectoId(Long proyectoId);
}
