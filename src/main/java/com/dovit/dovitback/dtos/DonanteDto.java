package com.dovit.dovitback.dtos;

import lombok.Data;

@Data
public class DonanteDto {
    private Long id;
    private String nombreCompleto;
    private String contactoTelefonico;
    private String departamento;
    private String distrito;
    private String direccion;
    private Integer UsuarioId;
}
