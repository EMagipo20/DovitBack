package com.dovit.dovitback.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

//-> LUIS ANGEL
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonacionDto {
    private Long id;
    private Integer cantidadMonetaria;
    private Date fechaDonacion;
    private String metodoDePago;
    private Long donanteId;
    private Long proyectoId;
}
