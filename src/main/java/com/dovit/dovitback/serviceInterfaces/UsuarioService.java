package com.dovit.dovitback.serviceInterfaces;

import com.dovit.dovitback.model.Usuario;

import java.util.List;

public interface UsuarioService {
    public void insert(Usuario usuario);

    public List<Usuario> list();

    public void delete(int idUsuario);

    public Usuario listarId(int idUsuario);
}
