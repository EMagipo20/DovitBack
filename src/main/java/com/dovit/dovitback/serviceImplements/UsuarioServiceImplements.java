package com.dovit.dovitback.serviceImplements;
import com.dovit.dovitback.model.Usuario;
import com.dovit.dovitback.repositories.RolRepository;
import com.dovit.dovitback.repositories.UsuarioRepository;
import com.dovit.dovitback.serviceInterfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {
    @Autowired
    private UsuarioRepository uR;

    @Autowired
    private RolRepository rR;

    @Override
    public Usuario insert(Usuario usuario) {
        return uR.save(usuario);
    }

    @Override
    public void delete(int idUsuario) {
        uR.deleteById(idUsuario);
    }

    @Override
    public Usuario listarId(int idUsuario) {
        return uR.findById(idUsuario).orElse(new Usuario());
    }

    @Override
    public boolean existsByUsername(String username) {
        return uR.existsByUsername(username);
    }

    @Override
    public Usuario findByUsername(String username) {
        return uR.findByUsername(username);
    }

    @Override
    public List<Usuario> list() {
        return (List<Usuario>) uR.findAll();
    }
}