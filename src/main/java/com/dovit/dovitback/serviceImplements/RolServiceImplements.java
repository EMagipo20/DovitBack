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
    public Rol insert(Rol rol) {
        return rR.save(rol); // Asegúrate de devolver el rol guardado
    }

    @Override
    public void delete(int id) {
        rR.deleteById(id);
    }

    @Override
    public Rol listarId(int id) {
        return rR.findById(id).orElse(null);
    }

    @Override
    public List<Rol> list() {
        return (List<Rol>) rR.findAll();
    }
}
