package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Organizacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface OrganizacionRepository extends CrudRepository<Organizacion,Long> { ;
    List<Organizacion> findByDistrito(String distrito);

    //Buscar por departamento y distrito
    @Query("SELECT o FROM Organizacion o WHERE o.departamento = :departamento AND o.distrito = :distrito")
    List<Organizacion> findByDepartamentoAndDistrito(@Param("departamento") String departamento, @Param("distrito") String distrito);
}
