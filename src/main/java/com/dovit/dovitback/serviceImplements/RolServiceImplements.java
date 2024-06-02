package com.dovit.dovitback.serviceImplements;

import com.dovit.dovitback.model.Rol;
import com.dovit.dovitback.repositories.RolRepository;
import com.dovit.dovitback.serviceInterfaces.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImplements implements RolService {
    @Autowired
    private RolRepository rR;

    @Override
    public void insert(Rol rol) {
        rR.save(rol);
    }

    @Override
    public List<Rol> list() {
        return rR.findAll();
    }

    @Override
    public void delete(int idRol) {
        rR.deleteById(idRol);
    }

    @Override
    public Rol listarId(int idRol) {
        return rR.findById(idRol).orElse(new Rol());
    }
}
