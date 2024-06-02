package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Donaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//-> LUIS ANGEL
@Repository
public interface DonacionRepository extends JpaRepository<Donaciones, Long> {
    @Query(value="SELECT * \n" +
            "FROM donaciones\n" +
            "WHERE EXTRACT(YEAR FROM fecha_donacion) = EXTRACT(YEAR FROM CURRENT_DATE) AND donaciones.id_donante = :idDonante", nativeQuery = true)
    List<Donaciones> donaciones(@Param("idDonante") Long idDonante);

    @Query("SELECT d.proyectos.id, COUNT(d) FROM Donaciones d GROUP BY d.proyectos.id")
    List<Object[]> contarDonacionesPorProyecto();
}
