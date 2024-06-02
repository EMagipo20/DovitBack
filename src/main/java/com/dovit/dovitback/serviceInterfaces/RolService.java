package com.dovit.dovitback.serviceInterfaces;

import com.dovit.dovitback.model.Rol;
import java.util.List;

public interface RolService {
    public void insert(Rol rol);

    public List<Rol> list();

    public void delete(int idRol);

    public Rol listarId(int idRol);
}
