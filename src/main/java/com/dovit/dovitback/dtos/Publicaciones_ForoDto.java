package com.dovit.dovitback.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//-> HITALO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicaciones_ForoDto {
    private Long id;
    private String titulo;
    private String contenido;
    private Long OrganizacionId;
    private Long ForosId;
}
