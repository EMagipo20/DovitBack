package com.dovit.dovitback.dtos;

import lombok.Data;

@Data
public class OrganizacionDto {
    private Long id;
    private String nombreOrganizacion;
    private String departamento;
    private String distrito;
    private String direccion;
    private int cantidadProyectos;
    private String nombreRepresentante;
    private String numeroTelefonicoRepresentante;
    private String correoElectronicoRepresentante;
    private Integer UsuarioId;
}
