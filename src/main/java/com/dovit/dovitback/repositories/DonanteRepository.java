package com.dovit.dovitback.repositories;

import com.dovit.dovitback.model.Donante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonanteRepository extends CrudRepository<Donante, Long> {
    List<Donante> findByDistrito(String distrito);

    // Buscar por nombre completo del donante
    @Query("SELECT count(d) FROM Donante d WHERE d.nombreCompleto = :nombreCompleto")
    public Long ContarDonantesPorNombreCompleto(@Param("nombreCompleto") String nombreCompleto);

    //Buscar donante por departamento y distrito
    @Query("SELECT o FROM Donante o WHERE o.departamento = :departamento AND o.distrito = :distrito")
    List<Donante> findByDepartamentoAndDistrito(@Param("departamento") String departamento, @Param("distrito") String distrito);
}
