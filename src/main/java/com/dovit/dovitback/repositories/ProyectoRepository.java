package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Comentarios;
import com.dovit.dovitback.model.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//-> KIARA
@Repository
public interface ProyectoRepository extends JpaRepository<Proyectos, Long>{
    @Query("SELECT p.categoria, COUNT(p) FROM Proyectos p GROUP BY p.categoria")
    List<Object[]> contarProyectosPorCategoria();

    //-> Proyectos segun organizacion id
    @Query("SELECT c FROM Proyectos c WHERE c.organizacion.id = :idOrganizacion")
    List<Proyectos> BuscarPorProyectoId(Long idOrganizacion);
}
