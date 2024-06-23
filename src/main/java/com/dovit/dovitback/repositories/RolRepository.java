package com.dovit.dovitback.repositories;
import com.dovit.dovitback.model.Rol;
import com.dovit.dovitback.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
